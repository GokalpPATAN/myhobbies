package com.patan.myhobbies.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.patan.navigation.AppRoute
import com.patan.navigation.HomeScreenRoute
import com.patan.navigation.LoginScreenRoute
import com.patan.navigation.RegisterScreenRoute
import com.patan.myhobbies.feature.auth.presentation.navigation.loginScreen
import com.patan.myhobbies.feature.auth.presentation.navigation.registerScreen
import com.patan.myhobbies.feature.home.presentation.navigation.homeScreen

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
                homeScreen(
                    navigateToHobbyDetail = { hobbyId ->
//                        navigationController.navigate(HobbyDetailRoute(hobbyId))
                    }
                )
            }
        )
    }
}