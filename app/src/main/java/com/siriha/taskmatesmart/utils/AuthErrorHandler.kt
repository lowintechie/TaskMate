package com.siriha.taskmatesmart.utils

import android.content.Context
import android.widget.Toast
import android.util.Log

object AuthErrorHandler {
    
    fun showGoogleSignInConfigurationError(context: Context) {
        Toast.makeText(
            context,
            "Google Sign-In not configured. Please contact support.",
            Toast.LENGTH_LONG
        ).show()
        Log.e("AuthErrorHandler", "Google Sign-In Web Client ID not configured")
    }
    
    fun showGoogleSignInError(context: Context, error: String) {
        Toast.makeText(
            context,
            "Google Sign-In failed: $error",
            Toast.LENGTH_LONG
        ).show()
        Log.e("AuthErrorHandler", "Google Sign-In error: $error")
    }
    
    fun showNetworkError(context: Context) {
        Toast.makeText(
            context,
            "Network error. Please check your connection and try again.",
            Toast.LENGTH_LONG
        ).show()
    }
    
    fun showGenericError(context: Context, message: String = "An error occurred") {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()
    }
}