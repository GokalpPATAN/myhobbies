package com.patan.myhobbies.feature.auth.data.google

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.patan.myhobbies.feature.auth.domain.manager.GoogleSignInHandler
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class GoogleAuthManager @Inject constructor(
    @ApplicationContext private val context: Context,
    @Named("webClientId") private val webClientId: String,   // ✅ BURADA ALIYORUZ
) : GoogleSignInHandler {

    private val gso: GoogleSignInOptions =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)   // ✅ ARTIK BURADA KULLANIYORUZ
            .requestEmail()
            .build()

    private val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, gso)

    override fun getSignInIntent(): Intent = googleSignInClient.signInIntent

    fun getSignedInAccountFromIntent(data: Intent?): Task<GoogleSignInAccount> {
        return GoogleSignIn.getSignedInAccountFromIntent(data)
    }
}