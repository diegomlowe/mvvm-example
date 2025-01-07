package com.example.mvvmexample.data.network

import com.example.mvvmexample.data.network.dto.PostDto
import com.example.mvvmexample.data.network.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {
    @GET("users")
    suspend fun getUsers(): List<UserDto>

    @GET("users/{userId}")
    suspend fun getUserDetail(@Path("userId") userId: String): UserDto

    @GET("posts")
    suspend fun getPosts(): List<PostDto>

    @GET("posts/{postId}")
    suspend fun getPostDetail(@Path("postId") postId: String): PostDto

}