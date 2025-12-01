package com.patan.myhobbies.feature.home.presentation


import androidx.lifecycle.viewModelScope
import com.patan.myhobbies.feature.home.domain.repository.HomeRepository
import com.patan.myhobbies.feature.home.presentation.HomeScreenContract.SideEffect.ShowError
import core.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import model.RestResult
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : CoreViewModel<HomeScreenContract.State, HomeScreenContract.SideEffect, HomeScreenContract.Event>(
    HomeScreenContract.State(isLoading = false)
) {

    // Şimdilik sabit; Firebase UID geldikten sonra buraya onu koyacağız
    private val userId: String = "user_1"

    init {
        // Ekran açıldığında ilk yüklemeyi başlat
        loadInitial()
    }

    override fun setEvent(event: HomeScreenContract.Event) {
        when (event) {
            HomeScreenContract.Event.OnRefreshClicked -> {
                loadInitial()
            }

            is HomeScreenContract.Event.OnCategorySelected -> {
                updateState { it.copy(selectedCategory = event.category) }
                loadHobbiesForCategory(event.category.id)
            }

            is HomeScreenContract.Event.OnHobbyClicked -> {
                setSideEffect(
                    HomeScreenContract.SideEffect.NavigateToHobbyDetail(
                        hobbyId = event.hobby.id
                    )
                )
            }
        }
    }

    private fun loadInitial() {
        viewModelScope.launch {
            // 1) Kategorileri çek
            loadCategories()

            // 2) Kullanıcı özetini paralel veya sırayla çekebilirsin
            loadUserSummary()
        }
    }

    private suspend fun loadCategories() {
        homeRepository
            .getCategories()
            .collect { result ->
                when (result) {
                    is RestResult.Loading -> {
                        updateState { it.copy(isLoading = true, errorMessage = null) }
                    }

                    is RestResult.Error -> {
                        updateState {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.error.message ?: "Kategoriler yüklenemedi"
                            )
                        }
                        setSideEffect(
                            ShowError(
                                result.error.message ?: "Kategoriler yüklenemedi"
                            )
                        )
                    }

                    is RestResult.Success -> {
                        val categories = result.result
                        // İlk kategoriyi seç
                        val first = categories.firstOrNull()

                        updateState {
                            it.copy(
                                isLoading = false,
                                categories = categories,
                                selectedCategory = first,
                                errorMessage = null
                            )
                        }

                        // İlk kategori için hobileri doldur
                        if (first != null) {
                            loadHobbiesForCategory(first.typeCode)
                        }
                    }

                    is RestResult.Progress -> TODO()
                }
            }
    }

    private fun loadHobbiesForCategory(categoryId: Int) {
        viewModelScope.launch {
            homeRepository
                .getHobbiesByCategory(categoryId)
                .collect { result ->
                    when (result) {
                        is RestResult.Loading -> {
                            updateState { it.copy(isLoading = true, errorMessage = null) }
                        }

                        is RestResult.Error -> {
                            updateState {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = result.error.message ?: "Hobiler yüklenemedi"
                                )
                            }
                            setSideEffect(
                                ShowError(
                                    result.error.message ?: "Hobiler yüklenemedi"
                                )
                            )
                        }

                        is RestResult.Success -> {
                            updateState {
                                it.copy(
                                    isLoading = false,
                                    hobbies = result.result,
                                    errorMessage = null
                                )
                            }
                        }

                        is RestResult.Progress -> TODO()
                    }
                }
        }
    }

    private fun loadUserSummary() {
        viewModelScope.launch {
            homeRepository
                .getUserSummary(userId)
                .collect { result ->
                    when (result) {
                        is RestResult.Loading -> {
                            // İstersen burada da loading gösterebilirsin, ama ekranı bozmamak için dokunmayabilirsin
                        }

                        is RestResult.Error -> {
                            // Özet alınamasa da ekran çalışsın; sadece toast göster
                            setSideEffect(
                                ShowError(
                                    result.error.message ?: "Kullanıcı özeti alınamadı"
                                )
                            )
                        }

                        is RestResult.Success -> {
                            updateState { it.copy(userSummary = result.result) }
                        }

                        is RestResult.Progress -> TODO()
                    }
                }
        }
    }
}