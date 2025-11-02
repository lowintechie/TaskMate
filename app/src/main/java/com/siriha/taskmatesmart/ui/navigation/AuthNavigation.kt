package com.siriha.taskmatesmart.ui.navigation

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.siriha.taskmatesmart.ui.screens.LoginScreen
import com.siriha.taskmatesmart.ui.screens.RegisterScreen
import com.siriha.taskmatesmart.utils.GoogleSignInHelper
import com.siriha.taskmatesmart.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun AuthNavigation(
    authViewModel: AuthViewModel = viewModel()
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val googleSignInHelper = remember { GoogleSignInHelper(context) }
    
    // Traditional Google Sign-In launcher as fallback
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val idToken = googleSignInHelper.handleSignInResult(result.data)
        if (idToken != null) {
            authViewModel.signInWithGoogle(idToken)
        } else {
            Log.e("GoogleSignIn", "Failed to get ID token from traditional Google Sign-In")
        }
    }
    
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onLoginClick = { email, password ->
                    authViewModel.signInEmail(email, password)
                },
                onGoogleLoginClick = {
                    if (!googleSignInHelper.isConfigured()) {
                        Log.e("GoogleSignIn", "Google Sign-In not configured. Please set up Web Client ID.")
                        return@LoginScreen
                    }
                    
                    scope.launch {
                        // Try Credential Manager first, fallback to traditional
                        val credentialManagerSuccess = performGoogleSignIn(context, authViewModel)
                        if (!credentialManagerSuccess) {
                            // Fallback to traditional Google Sign-In
                            try {
                                val signInIntent = googleSignInHelper.getSignInIntent()
                                googleSignInLauncher.launch(signInIntent)
                            } catch (e: Exception) {
                                Log.e("GoogleSignIn", "Failed to launch traditional Google Sign-In", e)
                            }
                        }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register")
                },
                authViewModel = authViewModel
            )
        }
        
        composable("register") {
            RegisterScreen(
                onRegisterClick = { name, email, password ->
                    authViewModel.signUpEmail(email, password)
                },
                onGoogleSignUpClick = {
                    if (!googleSignInHelper.isConfigured()) {
                        Log.e("GoogleSignIn", "Google Sign-In not configured. Please set up Web Client ID.")
                        return@RegisterScreen
                    }
                    
                    scope.launch {
                        // Try Credential Manager first, fallback to traditional
                        val credentialManagerSuccess = performGoogleSignIn(context, authViewModel)
                        if (!credentialManagerSuccess) {
                            // Fallback to traditional Google Sign-In
                            try {
                                val signInIntent = googleSignInHelper.getSignInIntent()
                                googleSignInLauncher.launch(signInIntent)
                            } catch (e: Exception) {
                                Log.e("GoogleSignIn", "Failed to launch traditional Google Sign-In", e)
                            }
                        }
                    }
                },
                onLoginClick = {
                    navController.navigate("login")
                },
                authViewModel = authViewModel
            )
        }
    }
}

private suspend fun performGoogleSignIn(context: Context, authViewModel: AuthViewModel): Boolean {
    return try {
        // Check if Web Client ID is configured
        Log.e("GoogleSignIn", "Web Client ID not configured. Please update Constants.WEB_CLIENT_ID")
        return false

        val credentialManager = CredentialManager.create(context)
        
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(com.siriha.taskmatesmart.utils.Constants.WEB_CLIENT_ID)
            .setAutoSelectEnabled(false)
            .build()
            
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
            
        val result = credentialManager.getCredential(
            request = request,
            context = context as Activity
        )
        
        val credential = result.credential
        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
        val idToken = googleIdTokenCredential.idToken
        
        authViewModel.signInWithGoogle(idToken)
        true
        
    } catch (e: GetCredentialException) {
        Log.e("GoogleSignIn", "Credential Manager failed, will try traditional method", e)
        when (e) {
            is androidx.credentials.exceptions.NoCredentialException -> {
                Log.e("GoogleSignIn", "No Google credentials available through Credential Manager")
            }
            else -> {
                Log.e("GoogleSignIn", "Credential exception: ${e.message}")
            }
        }
        false
    } catch (e: Exception) {
        Log.e("GoogleSignIn", "Unexpected error during Credential Manager Google Sign-In", e)
        false
    }
}