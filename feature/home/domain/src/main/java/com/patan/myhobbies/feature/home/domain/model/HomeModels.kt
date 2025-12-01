package com.patan.myhobbies.feature.home.domain.model

data class HobbyCategory(
    val id: Int,
    val name: String,     // "El Sanatları"
    val slug: String,     // "handcraft"
    val typeCode: Int,    // 0
    val typeName: String  // "Handcraft" (EN)
)

data class Hobby(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val difficulty: Int,
    val estimatedMinutes: Int,
    val xpReward: Int,
    val isActive: Boolean,
    val categoryId: Int
)

data class HobbyStep(
    val id: Int,
    val order: Int,
    val title: String,
    val description: String,
    val estimatedMinutes: Int,
    val xpReward: Int,
    val isCurrent: Boolean,
    val isCompleted: Boolean
)

data class UserSummary(
    val userId: String,
    val displayName: String,
    val email: String,
    val totalXp: Int,
    val level: Int,
    val completedHobbies: Int,
    val activeHobbies: Int
)

data class UserHobbyProgress(
    val id: Int,
    val userId: String,
    val hobbyId: Int,
    val currentStepOrder: Int,
    val totalXpEarned: Int,
    val isCompleted: Boolean
)

data class HobbyDetail(
    val hobby: Hobby,
    val progress: UserHobbyProgress?,
    val steps: List<HobbyStep>
)

data class HobbyProgressOverview(
    val currentStepOrder: Int,
    val totalXpEarned: Int,
    val isCompleted: Boolean
)

data class HobbyDetailForUser(
    val hobby: HobbyDetail,
    val progress: HobbyProgressOverview?,
    val steps: List<HobbyStep>
)