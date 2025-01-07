package com.example.mvvmexample.domain.usecase

import com.example.mvvmexample.base.BaseRemoteUseCase
import com.example.mvvmexample.base.Resource
import com.example.mvvmexample.domain.model.User
import com.example.mvvmexample.domain.model.toUserDomain
import com.example.mvvmexample.domain.repos.UsersRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRemoteUserDetailUseCase @Inject constructor(
    private val usersRepo: UsersRepo
) : BaseRemoteUseCase<User>() {
    operator fun invoke(userId: Int): Flow<Resource<User>> {
        return fetchData {
            usersRepo.getUserDetail(userId).toUserDomain()
        }
    }
}