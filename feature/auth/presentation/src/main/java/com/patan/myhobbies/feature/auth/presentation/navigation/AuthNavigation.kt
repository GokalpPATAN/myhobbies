package com.patan.myhobbies.feature.auth.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.patan.myhobbies.feature.auth.presentation.LoginScreen
import com.patan.myhobbies.feature.auth.presentation.RegisterScreen
import com.patan.navigation.LoginScreenRoute
import com.patan.navigation.RegisterScreenRoute

fun NavGraphBuilder.loginScreen(
    navigateToHome: () -> Unit,
    navigateToRegister: () -> Unit,
) {
    composable<LoginScreenRoute> {
        LoginScreen(
            navigateToHome = navigateToHome,
            navigateToRegister = navigateToRegister
        )
    }
}

fun NavGraphBuilder.registerScreen(
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
) {
    composable<RegisterScreenRoute> {
        RegisterScreen(
            navigateToHome = navigateToHome,
            navigateToLogin = navigateToLogin
        )
    }
}