package com.patan.myhobbies.feature.home.data.remote

import com.patan.myhobbies.feature.home.data.remote.dto.CategoryDto
import com.patan.myhobbies.feature.home.data.remote.dto.CreateUserRequestDto
import com.patan.myhobbies.feature.home.data.remote.dto.HobbyDetailDto
import com.patan.myhobbies.feature.home.data.remote.dto.HobbyDto
import com.patan.myhobbies.feature.home.data.remote.dto.HobbyStepDto
import com.patan.myhobbies.feature.home.data.remote.dto.UserHobbyProgressDto
import com.patan.myhobbies.feature.home.data.remote.dto.UserSummaryDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MyHobbiesApi {

    // Kategoriler
    @GET("/api/categories")
    suspend fun getCategories(): List<CategoryDto>

    // Bir kategorideki hobiler (type = El sanatları vs. int)
    @GET("/api/hobbies/by-category/{categoryId}")
    suspend fun getHobbiesByCategory(
        @Path("categoryId") categoryId: Int
    ): List<HobbyDto>

    // Bir hobinin adımları
    @GET("/api/hobbies/{hobbyId}/steps")
    suspend fun getHobbySteps(
        @Path("hobbyId") hobbyId: Int
    ): List<HobbyStepDto>

    // Kullanıcı oluştur (Firebase UID ile)
    @POST("/api/users")
    suspend fun createUser(
        @Body body: CreateUserRequestDto
    ): UserSummaryDto? // backend şimdilik sadece 201 + body döndürüyor, burada summary dönsün istersen sonra değiştirirsin

    // Kullanıcı özeti
    @GET("/api/users/{userId}/summary")
    suspend fun getUserSummary(
        @Path("userId") userId: String
    ): UserSummaryDto

    // Hobi detay + progress + adımlar
    @GET("/api/users/{userId}/hobbies/{hobbyId}/detail")
    suspend fun getHobbyDetail(
        @Path("userId") userId: String,
        @Path("hobbyId") hobbyId: Int
    ): HobbyDetailDto

    // Hobi başlat
    @POST("/api/users/{userId}/hobbies/{hobbyId}/start")
    suspend fun startHobby(
        @Path("userId") userId: String,
        @Path("hobbyId") hobbyId: Int
    ): UserHobbyProgressDto

    // Adım ilerlet (advance)
    @POST("/api/users/{userId}/hobbies/{hobbyId}/advance")
    suspend fun advanceHobby(
        @Path("userId") userId: String,
        @Path("hobbyId") hobbyId: Int
    ): UserHobbyProgressDto

}