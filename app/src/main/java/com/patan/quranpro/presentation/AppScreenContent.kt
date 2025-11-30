package com.patan.quranpro.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.patan.navigation.AppRoute
import com.patan.navigation.HomeScreenRoute

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
            startDestination = HomeScreenRoute::class,
            modifier = Modifier.padding(innerPadding),
            builder = {
                // Home
//                homeScreen(
//                    navigateToQuran = {
//                        navigationController.navigate(QuranScreenRoute)
//                    },
//                    navigateToQibla = {
//                        navigationController.navigate(QiblaScreenRoute)
//                    }
//                )

                // Quran
//                quranScreen()

                // Qibla / Tespih
//                qiblaScreen()
            }
        )
    }
}