package com.example.mvvmexample.data.network.datasource

import com.example.mvvmexample.data.network.dto.PostDto
import com.example.mvvmexample.data.network.dto.UserDto

interface RemoteDataSource {
    suspend fun getUsersList(): List<UserDto>
    suspend fun getUserDetail(userId: Int): UserDto
    suspend fun getPostsList(): List<PostDto>
    suspend fun getPostDetail(postId: Int): PostDto
}