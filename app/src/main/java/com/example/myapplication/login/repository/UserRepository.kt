package com.example.myapplication.login.repository

import com.example.myapplication.login.api.ApiService
import com.example.myapplication.login.model.LoginResponse

class UserRepository(private val apiService: ApiService) {
    suspend fun login(username: String, password: String): LoginResponse {
        return apiService.login(username, password)
    }
}
