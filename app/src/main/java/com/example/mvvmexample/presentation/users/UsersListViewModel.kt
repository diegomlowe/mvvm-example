package com.example.mvvmexample.presentation.users

import com.example.mvvmexample.base.BaseViewModel
import com.example.mvvmexample.domain.model.User
import com.example.mvvmexample.domain.usecase.GetRemoteUsersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    private val getRemoteUsersListUseCase: GetRemoteUsersListUseCase
) : BaseViewModel<List<User>>() {

    init {
        collectFlow(getRemoteUsersListUseCase())
    }
}
