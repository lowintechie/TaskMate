package com.siriha.taskmatesmart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.siriha.taskmatesmart.ui.navigation.AuthNavigation
import com.siriha.taskmatesmart.ui.theme.TaskMateSmartTheme
import com.siriha.taskmatesmart.viewmodel.AuthViewModel

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskMateSmartTheme {
                val authViewModel: AuthViewModel = viewModel()
                val context = LocalContext.current
                
                // Observe authentication state
                val authState by authViewModel.state.collectAsState()
                
                // Debug logging
                LaunchedEffect(authState) {
                    Log.d("AuthActivity", "Auth state changed: loading=${authState.loading}, success=${authState.success}, error=${authState.error}")
                }
                
                LaunchedEffect(authState.success) {
                    if (authState.success) {
                        Log.d("AuthActivity", "Authentication successful, navigating to MainActivity")
                        // Navigate to MainActivity on successful authentication
                        startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                        authViewModel.clearState() // Clear the success state
                        finish()
                    }
                }
                
                AuthNavigation(authViewModel = authViewModel)
            }
        }
    }
}