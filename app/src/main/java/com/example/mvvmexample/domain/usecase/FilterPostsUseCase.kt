package com.example.mvvmexample.domain.usecase

import com.example.mvvmexample.domain.model.Post
import javax.inject.Inject

class FilterPostsUseCase @Inject constructor() {
    operator fun invoke(postsList: List<Post>, filter: String): List<Post> {
        val lowercaseFilter = filter.lowercase()
        return postsList.filter { post ->
            listOf(post.title, post.content, post.slug).any {
                it.lowercase().contains(lowercaseFilter)
            }
        }
    }
}