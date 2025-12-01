package com.patan.myhobbies.feature.home.presentation

import com.patan.myhobbies.feature.home.domain.model.Hobby
import com.patan.myhobbies.feature.home.domain.model.HobbyCategory
import com.patan.myhobbies.feature.home.domain.model.UserSummary
import core.CoreState

internal object HomeScreenContract {

    sealed class Event : CoreState.Event {
        data object OnRefreshClicked : Event()
        data class OnCategorySelected(val category: HobbyCategory) : Event()
        data class OnHobbyClicked(val hobby: Hobby) : Event()
    }

    data class State(
        override val isLoading: Boolean,
        val categories: List<HobbyCategory> = emptyList(),
        val selectedCategory: HobbyCategory? = null,
        val hobbies: List<Hobby> = emptyList(),
        val userSummary: UserSummary? = null,
        val errorMessage: String? = null
    ) : CoreState.ViewState

    sealed class SideEffect : CoreState.SideEffect {
        data class NavigateToHobbyDetail(val hobbyId: Int) : SideEffect()
        data class ShowError(val message: String) : SideEffect()
    }
}