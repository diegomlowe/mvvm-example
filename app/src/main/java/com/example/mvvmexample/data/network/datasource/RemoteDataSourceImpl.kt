package com.example.mvvmexample.data.network.datasource

import com.example.mvvmexample.base.wrapNetworkExceptions
import com.example.mvvmexample.data.network.ApiClient
import com.example.mvvmexample.data.network.dto.PostDto
import com.example.mvvmexample.data.network.dto.UserDto
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiClient: ApiClient): RemoteDataSource {

    override suspend fun getUsersList(): List<UserDto> {
        return wrapNetworkExceptions {
            apiClient.getUsers()
        }
    }

    override suspend fun getUserDetail(userId: Int): UserDto {
        return wrapNetworkExceptions {
            apiClient.getUserDetail(userId.toString())
        }
    }

    override suspend fun getPostsList(): List<PostDto> {
        return wrapNetworkExceptions {
            apiClient.getPosts()
        }
    }

    override suspend fun getPostDetail(postId: Int): PostDto {
        return wrapNetworkExceptions {
            apiClient.getPostDetail(postId.toString())
        }
    }

}