package com.patan.myhobbies.feature.home.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.patan.navigation.HomeScreenRoute
import com.patan.myhobbies.feature.home.presentation.HomeScreen

fun NavGraphBuilder.homeScreen(
    navigateToHobbyDetail: (Int) -> Unit,
) {
    composable<HomeScreenRoute> {
        HomeScreen(
            onNavigateToHobbyDetail = navigateToHobbyDetail,
        )
    }
}