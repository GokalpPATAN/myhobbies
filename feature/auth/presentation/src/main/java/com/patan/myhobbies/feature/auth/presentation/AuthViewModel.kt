package com.patan.myhobbies.feature.auth.presentation

import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.patan.myhobbies.feature.auth.domain.manager.GoogleSignInHandler
import com.patan.myhobbies.feature.auth.domain.repository.AuthRepository
import core.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import requester.onError
import requester.onLoading
import requester.onSuccess
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
internal class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val googleSignInHandler: Provider<GoogleSignInHandler>,
) : CoreViewModel<AuthScreenContract.State, AuthScreenContract.SideEffect, AuthScreenContract.Event>(
    AuthScreenContract.State()
) {

    override fun setEvent(event: AuthScreenContract.Event) {
        when (event) {
            is AuthScreenContract.Event.OnLoginClicked ->
                login(event.email, event.pass)

            is AuthScreenContract.Event.OnRegisterClicked ->
                register(event.email, event.pass)

            is AuthScreenContract.Event.OnGoogleSignInResult ->
                handleGoogleSignInResult(event.data)

            AuthScreenContract.Event.RequestGoogleSignIn ->
                requestGoogleSignIn()
        }
    }

    // Google Sign-In intent'i oluşturup side-effect ile UI'ya gönderiyoruz
    private fun requestGoogleSignIn() {
        val signInIntent = googleSignInHandler.get().getSignInIntent()
        setSideEffect(AuthScreenContract.SideEffect.LaunchGoogleSignIn(signInIntent))
    }

    // Google'dan dönen sonucu alıp Firebase ile login ediyoruz
    private fun handleGoogleSignInResult(data: Intent?) {
        viewModelScope.launch {
            try {
                val account = GoogleSignIn
                    .getSignedInAccountFromIntent(data)
                    .getResult(ApiException::class.java)!!

                val idToken = account.idToken!!
                signInWithGoogle(idToken)
            } catch (e: ApiException) {
                setSideEffect(
                    AuthScreenContract.SideEffect.ShowErrorSnackbar(
                        "Google sign in failed: ${e.statusCode}"
                    )
                )
            }
        }
    }

    private fun login(email: String, pass: String) {
        authRepository.login(email, pass)
            .requester
            .onLoading {
                updateState { it.copy(isLoading = true) }
            }
            .onError { error ->
                updateState { it.copy(isLoading = false) }
                setSideEffect(
                    AuthScreenContract.SideEffect.ShowErrorSnackbar(
                        error.message ?: "Login failed"
                    )
                )
            }
            .callWithSuccess { success ->
                updateState { it.copy(isLoading = false) }
                if (success) {
                    setSideEffect(AuthScreenContract.SideEffect.NavigateToHome)
                } else {
                    setSideEffect(
                        AuthScreenContract.SideEffect.ShowErrorSnackbar("Authentication failed")
                    )
                }
            }
    }

    private fun register(email: String, pass: String) {
        authRepository.register(email, pass)
            .onLoading {
                updateState { it.copy(isLoading = true) }
            }
            .onError { error ->
                updateState { it.copy(isLoading = false) }
                setSideEffect(
                    AuthScreenContract.SideEffect.ShowErrorSnackbar(
                        error.message ?: "Register failed"
                    )
                )
            }
            .onSuccess { success, _, _ ->
                updateState { it.copy(isLoading = false) }
                if (success) {
                    setSideEffect(AuthScreenContract.SideEffect.NavigateToHome)
                } else {
                    setSideEffect(
                        AuthScreenContract.SideEffect.ShowErrorSnackbar("Registration failed")
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun signInWithGoogle(idToken: String) {
        authRepository.signInWithGoogle(idToken)
            .requester
            .onLoading {
                updateState { it.copy(isLoading = true) }
            }
            .onError { error ->
                updateState { it.copy(isLoading = false) }
                setSideEffect(
                    AuthScreenContract.SideEffect.ShowErrorSnackbar(
                        error.message ?: "Google sign-in failed"
                    )
                )
            }
            .callWithSuccess { success ->
                updateState { it.copy(isLoading = false) }
                if (success) {
                    setSideEffect(AuthScreenContract.SideEffect.NavigateToHome)
                } else {
                    setSideEffect(
                        AuthScreenContract.SideEffect.ShowErrorSnackbar("Google sign-in failed")
                    )
                }
            }
    }
}