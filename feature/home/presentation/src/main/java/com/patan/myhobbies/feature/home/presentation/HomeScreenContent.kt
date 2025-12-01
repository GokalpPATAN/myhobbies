package com.patan.myhobbies.feature.home.presentation

import CategoryRow
import HobbyCard
import SummarySection
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun HomeScreenContent(
    state: HomeScreenContract.State,
    setEvent: (HomeScreenContract.Event) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            state.isLoading && state.categories.isEmpty() -> {
                // İlk açılış loading
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.errorMessage != null && state.categories.isEmpty() -> {
                // Tamamen boş + hata
                Text(
                    text = state.errorMessage,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // Başlık
                    Text(
                        text = "Hobilerini Keşfet",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Kullanıcı özeti (XP, level, tamamlanan hobi)
                    SummarySection(summary = state.userSummary)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Kategoriler
                    if (state.categories.isNotEmpty()) {
                        Text(
                            text = "Kategoriler",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        CategoryRow(
                            categories = state.categories,
                            selected = state.selectedCategory,
                            onClick = { category ->
                                setEvent(HomeScreenContract.Event.OnCategorySelected(category))
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Hobi listesi
                    Text(
                        text = "Hobiler",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    if (state.hobbies.isEmpty()) {
                        Text(
                            text = "Bu kategoride henüz hobi yok.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(state.hobbies) { hobby ->
                                HobbyCard(
                                    hobby = hobby,
                                    onClick = {
                                        setEvent(HomeScreenContract.Event.OnHobbyClicked(hobby))
                                    }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}