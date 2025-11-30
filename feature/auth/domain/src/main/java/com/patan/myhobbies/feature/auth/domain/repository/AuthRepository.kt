package com.patan.myhobbies.feature.auth.domain.repository

import kotlinx.coroutines.flow.Flow
import model.RestResult

interface AuthRepository {
    fun login(email: String, pass: String): Flow<RestResult<Boolean>>
    fun register(email: String, pass: String): Flow<RestResult<Boolean>>
    fun signInWithGoogle(idToken: String): Flow<RestResult<Boolean>>
}