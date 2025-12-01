package com.patan.myhobbies.feature.home.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id: Int,
    val name: String,
    val slug: String,
    @SerialName("typeCode")
    val typeCode: Int,
    val typeName: String
)

@Serializable
data class HobbyDto(
    val id: Int,
    val title: String,
    val shortDescription: String,
    val difficulty: Int,
    val estimatedMinutes: Int,
    val xpReward: Int,
    val isActive: Boolean? = null,   // nullable + default
    val categoryId: Int
)

@Serializable
data class HobbyStepDto(
    val id: Int,
    val order: Int,
    val title: String,
    val description: String,
    val estimatedMinutes: Int,
    val xpReward: Int,
    val hobbyId: Int
)

@Serializable
data class UserSummaryDto(
    val userId: String,
    val displayName: String,
    val email: String,
    val totalXp: Int,
    val level: Int,
    val completedHobbies: Int,
    val activeHobbies: Int
)

@Serializable
data class UserHobbyProgressDto(
    val id: Int,
    val userId: String,
    val hobbyId: Int,
    val currentStepOrder: Int,
    val totalXpEarned: Int,
    val isCompleted: Boolean,
    val startedAt: String,
    val updatedAt: String
)

@Serializable
data class HobbyDetailStepDto(
    val id: Int,
    val order: Int,
    val title: String,
    val description: String,
    val estimatedMinutes: Int,
    val xpReward: Int,
    val isCurrent: Boolean,
    val isCompleted: Boolean
)

@Serializable
data class HobbyDetailDto(
    val hobby: HobbyDto,
    val progress: UserHobbyProgressDto? = null,
    val steps: List<HobbyDetailStepDto>
)

@Serializable
data class CreateUserRequestDto(
    val id: String,
    val displayName: String,
    val email: String
)

@Serializable
data class HobbyDetailForUserResponse(
    val hobby: HobbyDto,
    val progress: ProgressDto?,
    val steps: List<HobbyStepDto>
)

@Serializable
data class ProgressDto(
    val currentStepOrder: Int,
    val totalXpEarned: Int,
    val isCompleted: Boolean
)

@Serializable
data class CompleteStepResponse(
    val success: Boolean,
    val newXp: Int?,
    val completed: Boolean?
)