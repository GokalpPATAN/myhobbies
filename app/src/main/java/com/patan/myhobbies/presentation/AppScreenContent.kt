package com.patan.myhobbies.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.patan.myhobbies.feature.auth.presentation.navigation.loginScreen
import com.patan.myhobbies.feature.auth.presentation.navigation.registerScreen
import com.patan.navigation.AppRoute
import com.patan.navigation.HomeScreenRoute
import com.patan.navigation.LoginScreenRoute
import com.patan.navigation.RegisterScreenRoute

@Composable
internal fun AppScreenContent(
    navigationController: NavHostController,
    state: AppScreenContract.State,
    innerPadding: PaddingValues,
) {
    if (!state.keepSplashScreenOn) {
        NavHost(
            navController = navigationController,
            route = AppRoute::class,
            startDestination = LoginScreenRoute::class,
            modifier = Modifier.padding(innerPadding),
            builder = {
                loginScreen(
                    navigateToHome = {
                        navigationController.navigate(HomeScreenRoute) {
                            popUpTo(LoginScreenRoute) {
                                inclusive = true
                            }
                        }
                    },
                    navigateToRegister = {
                        navigationController.navigate(RegisterScreenRoute)
                    }
                )
                registerScreen(
                    navigateToHome = {
                        navigationController.navigate(HomeScreenRoute) {
                            popUpTo(LoginScreenRoute) {
                                inclusive = true
                            }
                        }
                    },
                    navigateToLogin = {
                        navigationController.popBackStack()
                    }
                )
                composable<HomeScreenRoute> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Home Screen")
                    }
                }
            }
        )
    }
}