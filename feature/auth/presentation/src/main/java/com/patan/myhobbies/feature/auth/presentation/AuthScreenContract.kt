package com.patan.myhobbies.feature.auth.presentation

import android.content.Intent
import core.CoreState

internal object AuthScreenContract {

    sealed class Event : CoreState.Event {
        data class OnLoginClicked(val email: String, val pass: String) : Event()
        data class OnRegisterClicked(val email: String, val pass: String) : Event()
        data class OnGoogleSignInResult(val data: Intent?) : Event()
        data object RequestGoogleSignIn : Event()
    }

    data class State(
        override val isLoading: Boolean = false
    ) : CoreState.ViewState

    sealed class SideEffect : CoreState.SideEffect {
        data object NavigateToHome : SideEffect()
        data class ShowErrorSnackbar(val message: String) : SideEffect()
        data class LaunchGoogleSignIn(val intent: Intent) : SideEffect()
    }
}