package com.example.mvvmexample.presentation.users

import com.example.mvvmexample.base.BaseViewModel
import com.example.mvvmexample.domain.model.User
import com.example.mvvmexample.domain.usecase.GetRemoteUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class UserLocationViewModel @Inject constructor(
    private val getRemoteUserDetailUseCase: GetRemoteUserDetailUseCase
) : BaseViewModel<User>() {

    fun getUserLocation(userId: Int) {
        collectFlow(getRemoteUserDetailUseCase(userId))
    }
}