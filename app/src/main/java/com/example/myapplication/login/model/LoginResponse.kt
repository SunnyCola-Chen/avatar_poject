package com.example.myapplication.login.model

data class LoginResponse(
    val userId: String,
    val token: String,
    val username: String
)