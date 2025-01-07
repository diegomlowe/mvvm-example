package com.example.mvvmexample.data.network.repoimpl

import com.example.mvvmexample.data.network.datasource.RemoteDataSource
import com.example.mvvmexample.data.network.dto.UserDto
import com.example.mvvmexample.domain.repos.UsersRepo
import javax.inject.Inject

class UsersRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : UsersRepo {
    override suspend fun getUsersList(): List<UserDto> = remoteDataSource.getUsersList()
    override suspend fun getUserDetail(userId:Int): UserDto =
        remoteDataSource.getUserDetail(userId)
}