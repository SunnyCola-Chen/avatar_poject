package com.example.myapplication.login.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.login.model.LoginResponse
import com.example.myapplication.login.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<LoginResponse>>(UiState.Loading)
    val state: StateFlow<UiState<LoginResponse>> = _state

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val result = userRepository.login(username, password)
                _state.value = UiState.Success(result)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "未知错误")
            }
        }
    }
}

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

