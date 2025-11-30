package com.patan.myhobbies.feature.auth.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun LoginScreen(
    navigateToHome: () -> Unit,
    navigateToRegister: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // 🔹 TextField state’leri burada tutuluyor
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val googleSignInLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = { result ->
                viewModel.setEvent(AuthScreenContract.Event.OnGoogleSignInResult(result.data))
            }
        )

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { effect ->
            handleAuthSideEffect(
                effect = effect,
                navigateToHome = navigateToHome,
                snackbarHostState = snackbarHostState,
                googleSignInLauncher = googleSignInLauncher,
                scope = scope
            )
        }
    }

    LoginScreenContent(
        state = state,
        snackbarHostState = snackbarHostState,
        email = email,
        onEmailChange = { email = it },
        password = password,
        onPasswordChange = { password = it },
        onEvent = viewModel::setEvent,
        onGoogleSignInClick = {
            viewModel.setEvent(AuthScreenContract.Event.RequestGoogleSignIn)
        },
        onNavigateToRegisterClick = navigateToRegister
    )
}