package com.patan.myhobbies.feature.auth.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.patan.myhobbies.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import model.BaseError
import model.RestResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override fun login(email: String, pass: String): Flow<RestResult<Boolean>> = flow {
        emit(RestResult.Loading)
        val result = firebaseAuth.signInWithEmailAndPassword(email, pass).await()
        emit(RestResult.Success(result.user != null))
    }.catch {
        emit(RestResult.Error(BaseError(it.message.toString())))
    }

    override fun register(email: String, pass: String): Flow<RestResult<Boolean>> = flow {
        emit(RestResult.Loading)
        val result = firebaseAuth.createUserWithEmailAndPassword(email, pass).await()
        emit(RestResult.Success(result.user != null))
    }.catch {
        emit(RestResult.Error(BaseError(it.message.toString())))
    }

    override fun signInWithGoogle(idToken: String): Flow<RestResult<Boolean>> = flow {
        emit(RestResult.Loading)
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val result = firebaseAuth.signInWithCredential(credential).await()
        emit(RestResult.Success(result.user != null))
    }.catch {
        emit(RestResult.Error(BaseError(it.message.toString())))
    }
}