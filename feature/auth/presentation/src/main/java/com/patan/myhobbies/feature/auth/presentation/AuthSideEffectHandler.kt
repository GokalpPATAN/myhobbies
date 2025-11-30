package com.patan.myhobbies.feature.auth.presentation

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal fun handleAuthSideEffect(
    effect: AuthScreenContract.SideEffect,
    navigateToHome: () -> Unit,
    snackbarHostState: SnackbarHostState,
    googleSignInLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    scope: CoroutineScope
) {
    when (effect) {
        is AuthScreenContract.SideEffect.NavigateToHome -> navigateToHome()

        is AuthScreenContract.SideEffect.ShowErrorSnackbar -> {
            scope.launch {
                snackbarHostState.showSnackbar(effect.message)
            }
        }

        is AuthScreenContract.SideEffect.LaunchGoogleSignIn -> {
            googleSignInLauncher.launch(effect.intent)
        }
    }
}