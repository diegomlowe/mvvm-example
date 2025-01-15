package com.example.mvvmexample.presentation.posts

import com.example.mvvmexample.base.BaseViewModel
import com.example.mvvmexample.base.Resource
import com.example.mvvmexample.domain.model.Post
import com.example.mvvmexample.domain.usecase.FilterPostsUseCase
import com.example.mvvmexample.domain.usecase.GetRemotePostsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsListViewModel @Inject constructor(
    private val getRemotePostsListUseCase: GetRemotePostsListUseCase,
    private val filterPostsUseCase: FilterPostsUseCase
) : BaseViewModel<List<Post>>() {

    private lateinit var unfilteredPostsList: List<Post>

    init {
        collectFlow(getRemotePostsListUseCase())
    }

    override fun handleSuccess(result: Resource.Success<List<Post>>) {
        super.handleSuccess(result)
        unfilteredPostsList = result.data
    }

    fun filterPosts(filter: String) {
        val filteredList = filterPostsUseCase.invoke(unfilteredPostsList, filter)
        updateUiState(data = filteredList)
    }
}