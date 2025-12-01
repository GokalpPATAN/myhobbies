package com.patan.myhobbies.feature.home.domain.repository

import com.patan.myhobbies.feature.home.domain.model.Hobby
import com.patan.myhobbies.feature.home.domain.model.HobbyCategory
import com.patan.myhobbies.feature.home.domain.model.HobbyDetail
import com.patan.myhobbies.feature.home.domain.model.UserHobbyProgress
import com.patan.myhobbies.feature.home.domain.model.UserSummary
import kotlinx.coroutines.flow.Flow
import model.RestResult

interface HomeRepository {

    fun getCategories(): Flow<RestResult<List<HobbyCategory>>>

    fun getHobbiesByCategory(categoryId: Int): Flow<RestResult<List<Hobby>>>
    fun getUserSummary(userId: String): Flow<RestResult<UserSummary>>

    fun getHobbyDetail(userId: String, hobbyId: Int): Flow<RestResult<HobbyDetail>>

    fun startHobby(userId: String, hobbyId: Int): Flow<RestResult<UserHobbyProgress>>

    fun advanceHobby(userId: String, hobbyId: Int): Flow<RestResult<UserHobbyProgress>>
}