package com.example.mvvmexample.domain.usecase

import com.example.mvvmexample.base.BaseRemoteUseCase
import com.example.mvvmexample.base.Resource
import com.example.mvvmexample.domain.model.Post
import com.example.mvvmexample.domain.model.toPostDomain
import com.example.mvvmexample.domain.repos.PostsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRemotePostsListUseCase @Inject constructor(
    private val postsRepo: PostsRepo
) : BaseRemoteUseCase<List<Post>>() {
    operator fun invoke(): Flow<Resource<List<Post>>> {
        return fetchData {
            postsRepo.getPostsList().map { it.toPostDomain() }
        }
    }
}