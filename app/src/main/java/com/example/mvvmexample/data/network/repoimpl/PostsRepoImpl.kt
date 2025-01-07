package com.example.mvvmexample.data.network.repoimpl

import com.example.mvvmexample.data.network.datasource.RemoteDataSource
import com.example.mvvmexample.data.network.dto.PostDto
import com.example.mvvmexample.domain.repos.PostsRepo
import javax.inject.Inject

class PostsRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : PostsRepo {
    override suspend fun getPostsList(): List<PostDto> = remoteDataSource.getPostsList()
    override suspend fun getPostDetail(postId: Int): PostDto = remoteDataSource.getPostDetail(postId)
}