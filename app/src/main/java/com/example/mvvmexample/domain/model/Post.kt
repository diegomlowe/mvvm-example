package com.example.mvvmexample.domain.model

import com.example.mvvmexample.data.network.dto.PostDto


data class Post(
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

fun PostDto.toPostDomain(): Post {
    return Post(
        id,
        slug,
        url,
        title,
        content,
        image,
        thumbnail,
        status,
        category,
        publishedAt,
        updatedAt,
        userId
    )
}