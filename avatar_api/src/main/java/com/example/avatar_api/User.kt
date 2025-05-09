package com.example.avatar_api

import com.example.avatar_api.core.BusinessData

class User(
    val uid: String,
    val business1: BusinessData? = null,
    val business2: BusinessData? = null,
)