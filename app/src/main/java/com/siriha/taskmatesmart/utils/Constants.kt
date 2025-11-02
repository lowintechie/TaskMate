package com.siriha.taskmatesmart.utils

object Constants {
    // Web Client ID from Firebase Console
    // This should be the Web client ID from Firebase Auth -> Sign-in method -> Google
    // NOT the API Key! Make sure this is the OAuth 2.0 Web client ID
    // Replace this with your actual Web Client ID from Firebase Auth -> Google provider
    // It should look like: "123456789-abc...xyz.apps.googleusercontent.com"
    // NOT an API key (which starts with "AIza...")
    const val WEB_CLIENT_ID = "991847914320-067fu7ncqrp4nmfso4jbh1ndaj5ln8qj.apps.googleusercontent.com"
    
    // Authentication error messages
    const val AUTH_ERROR_WEAK_PASSWORD = "Password should be at least 6 characters"
    const val AUTH_ERROR_INVALID_EMAIL = "Invalid email address"
    const val AUTH_ERROR_USER_NOT_FOUND = "User not found"
    const val AUTH_ERROR_WRONG_PASSWORD = "Incorrect password"
    const val AUTH_ERROR_EMAIL_ALREADY_IN_USE = "Email is already in use"
    const val AUTH_ERROR_NETWORK = "Network error. Please check your connection"
    const val AUTH_ERROR_UNKNOWN = "An unknown error occurred"
}