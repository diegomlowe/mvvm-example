package com.example.mvvmexample.data.network.dto

data class PostDto(
    val id: Int,
    val slug: String,
    val url: String,
    val title: String,
    val content: String,
    val image: String,
    val thumbnail: String,
    val status: String,
    val category: String,
    val publishedAt: String,
    val updatedAt: String,
    val userId: Int
)
