package com.example.requests.api

data class PostJson(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)