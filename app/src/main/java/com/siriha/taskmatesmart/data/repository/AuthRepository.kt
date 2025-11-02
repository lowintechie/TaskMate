package com.siriha.taskmatesmart.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.GoogleAuthProvider
import com.siriha.taskmatesmart.utils.Constants
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {
    val currentUser get() = auth.currentUser

    suspend fun signUpEmail(email: String, password: String) {
        try {
            if (password.length < 6) {
                throw Exception(Constants.AUTH_ERROR_WEAK_PASSWORD)
            }
            auth.createUserWithEmailAndPassword(email, password).await()
        } catch (e: FirebaseAuthException) {
            throw Exception(getErrorMessage(e.errorCode))
        }
    }

    suspend fun signInEmail(email: String, password: String) {
        try {
            Log.d("AuthRepository", "Starting email sign-in for: $email")
            auth.signInWithEmailAndPassword(email, password).await()
            Log.d("AuthRepository", "Email sign-in completed successfully")
        } catch (e: FirebaseAuthException) {
            Log.e("AuthRepository", "Firebase auth error: ${e.errorCode}", e)
            throw Exception(getErrorMessage(e.errorCode))
        } catch (e: Exception) {
            Log.e("AuthRepository", "Unexpected error during sign-in", e)
            throw e
        }
    }

    suspend fun signInWithGoogle(idToken: String) {
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(credential).await()
        } catch (e: FirebaseAuthException) {
            throw Exception(getErrorMessage(e.errorCode))
        }
    }

    fun signOut() { auth.signOut() }

    private fun getErrorMessage(errorCode: String): String {
        return when (errorCode) {
            "ERROR_WEAK_PASSWORD" -> Constants.AUTH_ERROR_WEAK_PASSWORD
            "ERROR_INVALID_EMAIL" -> Constants.AUTH_ERROR_INVALID_EMAIL
            "ERROR_USER_NOT_FOUND" -> Constants.AUTH_ERROR_USER_NOT_FOUND
            "ERROR_WRONG_PASSWORD" -> Constants.AUTH_ERROR_WRONG_PASSWORD
            "ERROR_EMAIL_ALREADY_IN_USE" -> Constants.AUTH_ERROR_EMAIL_ALREADY_IN_USE
            "ERROR_NETWORK_REQUEST_FAILED" -> Constants.AUTH_ERROR_NETWORK
            else -> Constants.AUTH_ERROR_UNKNOWN
        }
    }
}