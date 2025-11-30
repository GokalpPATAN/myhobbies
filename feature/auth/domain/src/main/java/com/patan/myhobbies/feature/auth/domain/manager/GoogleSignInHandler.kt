package com.patan.myhobbies.feature.auth.domain.manager

import android.content.Intent

interface GoogleSignInHandler {
    fun getSignInIntent(): Intent
}