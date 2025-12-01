package com.patan.myhobbies.feature.home.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun HomeScreen(
    onNavigateToHobbyDetail: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    // SideEffect'ler (Toast + navigation vs.)
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { effect ->
            when (effect) {
                is HomeScreenContract.SideEffect.ShowError -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_LONG).show()
                }

                is HomeScreenContract.SideEffect.NavigateToHobbyDetail -> {
                    onNavigateToHobbyDetail(effect.hobbyId)
                }
            }
        }
    }

    HomeScreenContent(
        state = state,
        setEvent = viewModel::setEvent
    )
}