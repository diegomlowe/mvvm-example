package com.example.mvvmexample.data.network.repoimpl

import com.example.mvvmexample.base.NetworkException
import com.example.mvvmexample.data.network.datasource.RemoteDataSource
import com.example.mvvmexample.data.network.dto.PostDto
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException
import kotlin.test.assertFailsWith

class PostsRepoImplTest {

    private lateinit var postsRepo: PostsRepoImpl
    private lateinit var remoteDataSource: RemoteDataSource

    val mockPostDto = PostDto(
        id = 123,
        slug = "test-post",
        url = "https://example.com/test-post",
        title = "Test Post",
        content = "This is the content of the test post.",
        image = "https://example.com/images/test-post.jpg",
        thumbnail = "https://example.com/images/test-post-thumbnail.jpg",
        status = "published",
        category = "Technology",
        publishedAt = "2024-01-01T10:00:00Z",
        updatedAt = "2024-01-02T15:30:00Z",
        userId = 123
    )

    @Before
    fun setup() {
        remoteDataSource = mock()
        postsRepo = PostsRepoImpl(remoteDataSource)
    }

    @Test
    fun `getPostsList should return success when remoteDataSource returns data`() =
        runTest {

            val mockPosts = listOf(mockPostDto)
            `when`(remoteDataSource.getPostsList()).thenReturn(mockPosts)

            val result = postsRepo.getPostsList()

            assertTrue(result == mockPosts)
            verify(remoteDataSource).getPostsList()
        }

    @Test
    fun `getPostDetail should return success when remoteDataSource returns data`() =
        runTest {

            val mockPost = mockPostDto
            `when`(remoteDataSource.getPostDetail(1)).thenReturn(mockPost)

            val result = postsRepo.getPostDetail(1)

            assertTrue(result == mockPost)
            verify(remoteDataSource).getPostDetail(1)
        }

    @Test
    fun `getPostsList should propagate HttpException wrapped in NetworkException thrown by remoteDataSource`() =
        runTest {

            val httpException: HttpException = mock()
            `when`(remoteDataSource.getPostsList()).thenThrow(
                NetworkException(
                    "Server error occurred",
                    httpException
                )
            )

            val exception = assertFailsWith<NetworkException> {
                postsRepo.getPostsList()
            }

            assertTrue(exception.cause is HttpException)
            verify(remoteDataSource).getPostsList()
        }

    @Test

    fun `getPostsDetail should propagate HttpException wrapped in NetworkException thrown by remoteDataSource`() =
        runTest {

            val httpException: HttpException = mock()
            `when`(remoteDataSource.getPostDetail(1)).thenThrow(
                NetworkException(
                    "Server error occurred",
                    httpException
                )
            )

            val exception = assertFailsWith<NetworkException> {
                postsRepo.getPostDetail(1)
            }

            assertTrue(exception.cause is HttpException)
            verify(remoteDataSource).getPostDetail(1)
        }

    @Test
    fun `getPostsList should propagate IOException wrapped in NetworkException thrown by remoteDataSource`() =
        runTest {

            val ioException: IOException = mock()
            `when`(remoteDataSource.getPostsList()).thenThrow(
                NetworkException(
                    "Network error occurred, check your internet connection",
                    ioException
                )
            )

            val exception = assertFailsWith<NetworkException> {
                postsRepo.getPostsList()
            }

            assertTrue(exception.cause is IOException)
            verify(remoteDataSource).getPostsList()
        }

    @Test
    fun `getPostDetail should propagate IOException wrapped in NetworkException thrown by remoteDataSource`() =
        runTest {

            val ioException: IOException = mock()
            `when`(remoteDataSource.getPostDetail(1)).thenThrow(
                NetworkException(
                    "Network error occurred, check your internet connection",
                    ioException
                )
            )

            val exception = assertFailsWith<NetworkException> {
                postsRepo.getPostDetail(1)
            }

            assertTrue(exception.cause is IOException)
            verify(remoteDataSource).getPostDetail(1)
        }

    @Test
    fun `getPostsList should propagate TimeoutException wrapped in NetworkException thrown by remoteDataSource`() =
        runTest {

            val timeoutException: TimeoutException = mock()
            `when`(remoteDataSource.getPostsList()).thenThrow(
                NetworkException(
                    "Request timed out. Please try again later.",
                    timeoutException
                )
            )

            val exception = assertFailsWith<NetworkException> {
                postsRepo.getPostsList()
            }

            assertTrue(exception.cause is TimeoutException)
            verify(remoteDataSource).getPostsList()
        }

    @Test
    fun `getPostDetail should propagate TimeoutException wrapped in NetworkException thrown by remoteDataSource`() =
        runTest {

            val timeoutException: TimeoutException = mock()
            `when`(remoteDataSource.getPostDetail(1)).thenThrow(
                NetworkException(
                    "Request timed out. Please try again later.",
                    timeoutException
                )
            )

            val exception = assertFailsWith<NetworkException> {
                postsRepo.getPostDetail(1)
            }

            assertTrue(exception.cause is TimeoutException)
            verify(remoteDataSource).getPostDetail(1)
        }

    @Test
    fun `getPostsList should propagate generic Exception wrapped in NetworkException thrown by remoteDataSource`() =
        runTest {

            val genericException: Exception = mock()
            `when`(remoteDataSource.getPostsList()).thenThrow(
                NetworkException(
                    "An unexpected error occurred:",
                    genericException
                )
            )

            val exception = assertFailsWith<NetworkException> {
                postsRepo.getPostsList()
            }

            assertTrue(exception.cause is Exception)
            verify(remoteDataSource).getPostsList()
        }

    @Test
    fun `getPostDetail should propagate generic Exception wrapped in NetworkException thrown by remoteDataSource`() =
        runTest {

            val genericException: Exception = mock()
            `when`(remoteDataSource.getPostDetail(1)).thenThrow(
                NetworkException(
                    "An unexpected error occurred:",
                    genericException
                )
            )

            val exception = assertFailsWith<NetworkException> {
                postsRepo.getPostDetail(1)
            }

            assertTrue(exception.cause is Exception)
            verify(remoteDataSource).getPostDetail(1)
        }
}