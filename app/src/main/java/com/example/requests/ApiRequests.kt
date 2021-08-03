package com.example.requests

import com.example.requests.api.PostJson
import com.example.requests.api.UserJson
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiRequests {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @POST("/posts")
    fun createPost(@Body postJson: PostJson): Call<PostJson>

    @GET("/users/{id}")
    fun getUser(@Path("id") id: Int): Call<UserJson>

    @GET("/users")
    fun getUsers(): Call<List<UserJson>>
}