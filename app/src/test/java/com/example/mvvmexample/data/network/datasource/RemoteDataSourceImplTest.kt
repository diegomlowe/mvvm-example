package com.example.mvvmexample.data.network.datasource

import com.example.mvvmexample.base.NetworkException
import com.example.mvvmexample.base.wrapNetworkExceptions
import com.example.mvvmexample.data.network.ApiClient
import com.example.mvvmexample.data.network.dto.AddressDto
import com.example.mvvmexample.data.network.dto.CompanyDto
import com.example.mvvmexample.data.network.dto.GeoDto
import com.example.mvvmexample.data.network.dto.LoginDto
import com.example.mvvmexample.data.network.dto.PostDto
import com.example.mvvmexample.data.network.dto.UserDto
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException
import kotlin.test.assertFailsWith


@OptIn(ExperimentalCoroutinesApi::class)
class RemoteDataSourceImplTest {


    private lateinit var remoteDataSource: RemoteDataSourceImpl

    @MockK
    private lateinit var apiClient: ApiClient

    val mockUserDto = UserDto(
        id = 1,
        firstname = "John",
        lastname = "Doe",
        email = "john.doe@example.com",
        birthDate = "1990-01-01",
        login = LoginDto(
            uuid = "123e4567-e89b-12d3-a456-426614174000",
            username = "johndoe",
            password = "securePassword123",
            md5 = "5f4dcc3b5aa765d61d8327deb882cf99",
            sha1 = "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8",
            registered = "2024-01-01T12:00:00Z"
        ),
        address = AddressDto(
            street = "123 Elm Street",
            suite = "Apt 456",
            city = "Springfield",
            zipcode = "62704",
            geo = GeoDto(
                lat = "39.7817",
                lng = "-89.6501"
            )
        ),
        phone = "+1-555-123-4567",
        website = "https://johndoe.com",
        company = CompanyDto(
            name = "Doe Enterprises",
            catchPhrase = "Innovating the future",
            bs = "synergizing business solutions"
        )
    )

    private val mockPostDto = PostDto(
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

        Dispatchers.setMain(UnconfinedTestDispatcher())
        apiClient = mock(ApiClient::class.java)

        remoteDataSource = RemoteDataSourceImpl(apiClient)
    }

    @Test
    fun `getUsersList should return success when apiClient returns data`() = runTest {

        val mockUsers = listOf(mockUserDto)
        `when`(apiClient.getUsers()).thenReturn(mockUsers)

        val result = remoteDataSource.getUsersList()

        assertEquals(mockUsers, result)
        verify(apiClient).getUsers()
    }

    @Test
    fun `getUserDetail should return success when apiClient returns data`() = runTest {

        `when`(apiClient.getUserDetail("1")).thenReturn(mockUserDto)

        val result = remoteDataSource.getUserDetail(1)

        assertEquals(mockUserDto, result)
        verify(apiClient).getUserDetail("1")
    }

    @Test
    fun `getPostsList should return success when apiClient returns data`() = runTest {

        val mockPosts = listOf(mockPostDto)
        `when`(apiClient.getPosts()).thenReturn(mockPosts)

        val result = remoteDataSource.getPostsList()

        assertEquals(mockPosts, result)
        verify(apiClient).getPosts()
    }

    @Test
    fun `getPostDetail should return success when apiClient returns data`() = runTest {

        `when`(apiClient.getPostDetail("123")).thenReturn(mockPostDto)

        val result = remoteDataSource.getPostDetail(123)

        assertEquals(mockPostDto, result)
        verify(apiClient).getPostDetail("123")
    }

    @Test
    fun `getUserDetail should throw NetworkException when apiClient throws HttpException`() =
        runTest {

            val httpException = HttpException(mock())
            `when`(apiClient.getUserDetail("1")).thenThrow(httpException)

            val exception = assertFailsWith<NetworkException> {
                remoteDataSource.getUserDetail(1)
            }

            assertTrue(exception.cause is HttpException)
            verify(apiClient).getUserDetail("1")
        }

    @Test
    fun `wrapNetworkExceptions should wrap unknown exceptions into NetworkException`() {
        val unknownException = Exception("Unknown error")
        val result = runCatching {
            wrapNetworkExceptions { throw unknownException }
        }

        val networkException = result.exceptionOrNull()
        assertTrue(networkException is NetworkException)
        assertEquals("An unexpected error occurred: Unknown error", networkException?.message)
        assertEquals(unknownException, networkException?.cause)
    }

    @Test
    fun `wrapNetworkExceptions should wrap IOException into NetworkException`() {
        val ioException = IOException("Network error")
        val result = runCatching {
            wrapNetworkExceptions { throw ioException }
        }

        val networkException = result.exceptionOrNull()
        assertTrue(networkException is NetworkException)
        assertEquals(
            "Network error occurred, check your internet connection",
            networkException?.message
        )
        assertEquals(ioException, networkException?.cause)
    }

    @Test
    fun `wrapNetworkExceptions should wrap TimeoutException into NetworkException`() {
        val timeoutException = TimeoutException("Request timed out")
        val result = runCatching {
            wrapNetworkExceptions { throw timeoutException }
        }

        val networkException = result.exceptionOrNull()
        assertTrue(networkException is NetworkException)
        assertEquals("Request timed out. Please try again later.", networkException?.message)
        assertEquals(timeoutException, networkException?.cause)
    }

}