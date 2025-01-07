package com.example.mvvmexample.presentation.posts

import com.example.mvvmexample.base.BaseViewModel
import com.example.mvvmexample.domain.model.Post
import com.example.mvvmexample.domain.usecase.GetRemotePostDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val getRemotePostDetailUseCase: GetRemotePostDetailUseCase
) : BaseViewModel<Post>() {

    fun getPostDetail(postId: Int) {
        collectFlow(getRemotePostDetailUseCase(postId))
    }
}
