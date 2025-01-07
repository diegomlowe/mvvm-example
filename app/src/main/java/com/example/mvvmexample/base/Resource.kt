package com.example.mvvmexample.base


import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val message: String, val data: T? = null) : Resource<T>()
    data class Loading<out T>(val data: T? = null) : Resource<T>()
}

class NetworkException(message: String, cause: Throwable) : RuntimeException(message, cause)

inline fun <T> wrapNetworkExceptions(action: () -> T): T {
    return try {
        action()
    } catch (e: IOException) {
        throw NetworkException("Network error occurred, check your internet connection", e)
    } catch (e: HttpException) {
        throw NetworkException("Server error occurred: ${e.message}", e)
    } catch (e: TimeoutException) {
        throw NetworkException("Request timed out. Please try again later.", e)
    } catch (e: Exception) {
        throw NetworkException("An unexpected error occurred: ${e.localizedMessage}", e)
    }
}