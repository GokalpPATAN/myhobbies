package com.patan.myhobbies.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.patan.navigation.AppRoute
import com.patan.navigation.HomeScreenRoute
import com.patan.myhobbies.ui.theme.QuranTheme


@Composable
internal fun AppScreen(viewModel: AppScreenViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navigationController = rememberNavController()

    if (!state.keepSplashScreenOn) {
        AppScaffold(
            navigationController = navigationController,
            state = state,
        )
    }
}

@Composable
private fun AppScaffold(
    navigationController: NavHostController,
    state: AppScreenContract.State,
) {
    QuranTheme {
        Scaffold(
            bottomBar = {
                AppBottomBar(navController = navigationController)
            }
        ) { innerPadding ->
            AppScreenContent(
                navigationController = navigationController,
                state = state,
                innerPadding = innerPadding
            )
        }
    }
}

private data class BottomNavItem(
    val routeClassName: String,
    val label: String,
    val routeObj: Any,
    val icon: ImageVector,
)

@Composable
fun AppBottomBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem(
            routeClassName = HomeScreenRoute::class.qualifiedName!!,
            label = "Vakitler",
            routeObj = HomeScreenRoute,
            icon = Icons.Outlined.WatchLater
        ),
    )

    Surface(
        color = Color.Transparent,
        tonalElevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    // 🔸 Transparan–gradient arası arka plan
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.40f),
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.85f)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp)),
                containerColor = Color.Transparent,   // önemli
                tonalElevation = 0.dp
            ) {
                items.forEach { item ->
                    val selected = currentRoute == item.routeClassName

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.routeObj) {
                                popUpTo(AppRoute) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = { Text(text = item.label) },
                        alwaysShowLabel = false,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                        )
                    )
                }
            }
        }
    }
}