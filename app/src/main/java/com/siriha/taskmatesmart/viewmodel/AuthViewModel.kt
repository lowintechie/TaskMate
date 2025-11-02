package com.siriha.taskmatesmart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siriha.taskmatesmart.data.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class AuthUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)

class AuthViewModel(
    private val repo: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(AuthUiState())
    val state = _state.asStateFlow()

    fun signInEmail(email: String, password: String) = runAuth {
        repo.signInEmail(email.trim(), password)
    }

    fun signUpEmail(email: String, password: String) = runAuth {
        repo.signUpEmail(email.trim(), password)
    }

    fun signInWithGoogle(idToken: String) = runAuth {
        repo.signInWithGoogle(idToken)
    }

    fun signOut() = repo.signOut()

    private fun runAuth(block: suspend () -> Unit) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null, success = false)
            try {
                withContext(Dispatchers.IO) {
                    block()
                }
                _state.value = _state.value.copy(loading = false, success = true, error = null)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    loading = false,
                    success = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }
    
    fun clearState() {
        _state.value = AuthUiState()
    }
}