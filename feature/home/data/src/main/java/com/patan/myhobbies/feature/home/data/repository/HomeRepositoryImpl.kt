package com.patan.myhobbies.feature.home.data.repository

import com.patan.myhobbies.feature.home.data.mapper.toDomain
import com.patan.myhobbies.feature.home.data.remote.MyHobbiesApi
import com.patan.myhobbies.feature.home.domain.model.Hobby
import com.patan.myhobbies.feature.home.domain.model.HobbyCategory
import com.patan.myhobbies.feature.home.domain.model.HobbyDetail
import com.patan.myhobbies.feature.home.domain.model.UserHobbyProgress
import com.patan.myhobbies.feature.home.domain.model.UserSummary
import com.patan.myhobbies.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import model.BaseError
import model.RestResult
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: MyHobbiesApi
) : HomeRepository {

    override fun getCategories(): Flow<RestResult<List<HobbyCategory>>> = flow {
        emit(RestResult.Loading)
        val result = api.getCategories().map { it.toDomain() }
        emit(RestResult.Success(result))
    }.catch {
        emit(RestResult.Error(BaseError(it.message ?: "Unknown error")))
    }

    override fun getHobbiesByCategory(categoryId: Int): Flow<RestResult<List<Hobby>>> = flow {
        emit(RestResult.Loading)
        val result = api.getHobbiesByCategory(categoryId).map { it.toDomain() }
        emit(RestResult.Success(result))
    }.catch {
        emit(RestResult.Error(BaseError(it.message ?: "Unknown error")))
    }

    override fun getUserSummary(userId: String): Flow<RestResult<UserSummary>> = flow {
        emit(RestResult.Loading)
        val result = api.getUserSummary(userId).toDomain()
        emit(RestResult.Success(result))
    }.catch {
        emit(RestResult.Error(BaseError(it.message ?: "Unknown error")))
    }

    override fun getHobbyDetail(
        userId: String,
        hobbyId: Int
    ): Flow<RestResult<HobbyDetail>> = flow {
        emit(RestResult.Loading)
        val result = api.getHobbyDetail(userId, hobbyId).toDomain()
        emit(RestResult.Success(result))
    }.catch {
        emit(RestResult.Error(BaseError(it.message ?: "Unknown error")))
    }

    override fun startHobby(
        userId: String,
        hobbyId: Int
    ): Flow<RestResult<UserHobbyProgress>> = flow {
        emit(RestResult.Loading)
        val result = api.startHobby(userId, hobbyId).toDomain()
        emit(RestResult.Success(result))
    }.catch {
        emit(RestResult.Error(BaseError(it.message ?: "Unknown error")))
    }

    override fun advanceHobby(
        userId: String,
        hobbyId: Int
    ): Flow<RestResult<UserHobbyProgress>> = flow {
        emit(RestResult.Loading)
        val result = api.advanceHobby(userId, hobbyId).toDomain()
        emit(RestResult.Success(result))
    }.catch {
        emit(RestResult.Error(BaseError(it.message ?: "Unknown error")))
    }
}