package com.patan.myhobbies.feature.home.data.mapper

import com.patan.myhobbies.feature.home.data.remote.dto.CategoryDto
import com.patan.myhobbies.feature.home.data.remote.dto.HobbyDetailDto
import com.patan.myhobbies.feature.home.data.remote.dto.HobbyDetailStepDto
import com.patan.myhobbies.feature.home.data.remote.dto.HobbyDto
import com.patan.myhobbies.feature.home.data.remote.dto.UserHobbyProgressDto
import com.patan.myhobbies.feature.home.data.remote.dto.UserSummaryDto
import com.patan.myhobbies.feature.home.domain.model.Hobby
import com.patan.myhobbies.feature.home.domain.model.HobbyCategory
import com.patan.myhobbies.feature.home.domain.model.HobbyDetail
import com.patan.myhobbies.feature.home.domain.model.HobbyStep
import com.patan.myhobbies.feature.home.domain.model.UserHobbyProgress
import com.patan.myhobbies.feature.home.domain.model.UserSummary

fun CategoryDto.toDomain() = HobbyCategory(
    id = id,
    name = name,
    slug = slug,
    typeCode = typeCode,
    typeName = typeName
)

fun HobbyDto.toDomain() = Hobby(
    id = id,
    title = title,
    shortDescription = shortDescription,
    difficulty = difficulty,
    estimatedMinutes = estimatedMinutes,
    xpReward = xpReward,
    isActive = isActive ?: true,
    categoryId = categoryId
)

fun HobbyDetailStepDto.toDomain() = HobbyStep(
    id = id,
    order = order,
    title = title,
    description = description,
    estimatedMinutes = estimatedMinutes,
    xpReward = xpReward,
    isCurrent = isCurrent,
    isCompleted = isCompleted
)

fun UserSummaryDto.toDomain() = UserSummary(
    userId = userId,
    displayName = displayName,
    email = email,
    totalXp = totalXp,
    level = level,
    completedHobbies = completedHobbies,
    activeHobbies = activeHobbies
)

fun UserHobbyProgressDto.toDomain() = UserHobbyProgress(
    id = id,
    userId = userId,
    hobbyId = hobbyId,
    currentStepOrder = currentStepOrder,
    totalXpEarned = totalXpEarned,
    isCompleted = isCompleted
)

fun HobbyDetailDto.toDomain() = HobbyDetail(
    hobby = hobby.toDomain(),
    progress = progress?.toDomain(),
    steps = steps.map { it.toDomain() }
)