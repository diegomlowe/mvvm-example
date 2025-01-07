package com.example.mvvmexample.domain.usecase

import com.example.mvvmexample.domain.model.Post
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class FilterPostsUseCaseTest {

    private lateinit var filterPostsUseCase: FilterPostsUseCase

    @Before
    fun setUp() {
        filterPostsUseCase = FilterPostsUseCase()
    }

    val postsList = listOf(
        Post(
            id = 1,
            slug = "kotlin-tips",
            url = "https://example.com/kotlin-tips",
            title = "Kotlin Tips",
            content = "Learn advanced tips for Kotlin.",
            image = "https://example.com/images/kotlin-tips.jpg",
            thumbnail = "https://example.com/images/kotlin-thumbnail.jpg",
            status = "published",
            category = "Programming",
            publishedAt = "2024-01-01T10:00:00Z",
            updatedAt = "2024-01-02T10:00:00Z",
            userId = 101
        ),
        Post(
            id = 2,
            slug = "android-development",
            url = "https://example.com/android-development",
            title = "Android Development",
            content = "Learn about Jetpack Compose.",
            image = "https://example.com/images/android.jpg",
            thumbnail = "https://example.com/images/android-thumbnail.jpg",
            status = "published",
            category = "Mobile",
            publishedAt = "2024-01-03T10:00:00Z",
            updatedAt = "2024-01-04T10:00:00Z",
            userId = 102
        ),
        Post(
            id = 3,
            slug = "java-basics",
            url = "https://example.com/java-basics",
            title = "Java Basics",
            content = "Understand Java fundamentals.",
            image = "https://example.com/images/java.jpg",
            thumbnail = "https://example.com/images/java-thumbnail.jpg",
            status = "draft",
            category = "Programming",
            publishedAt = "2024-01-05T10:00:00Z",
            updatedAt = "2024-01-06T10:00:00Z",
            userId = 103
        )
    )

    @Test
    fun `invoke should return filtered posts based on title`() {

        val filter = "kotlin"

        val result = filterPostsUseCase(postsList, filter)

        assertEquals(1, result.size)
        assertEquals("Kotlin Tips", result.first().title)
    }

    @Test
    fun `invoke should return filtered posts based on content`() {

        val filter = "compose"

        val result = filterPostsUseCase(postsList, filter)

        assertEquals(1, result.size)
        assertEquals("Android Development", result.first().title)
    }

    @Test
    fun `invoke should return all posts when filter is empty`() {

        val filter = ""

        val result = filterPostsUseCase(postsList, filter)

        assertEquals(postsList.size, result.size)
        assertEquals(postsList, result)
    }

    @Test
    fun `invoke should return an empty list when no posts match the filter`() {

        val filter = "unmatched filter"

        val result = filterPostsUseCase(postsList, filter)

        assertEquals(0, result.size)
    }
}