package com.example.mvvmexample.domain.repos

import com.example.mvvmexample.data.network.dto.PostDto

interface PostsRepo {
    suspend fun getPostsList(): List<PostDto>
    suspend fun getPostDetail(postId: Int): PostDto
}