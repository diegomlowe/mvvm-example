package com.example.mvvmexample.domain.repos

import com.example.mvvmexample.data.network.dto.UserDto

interface UsersRepo {
    suspend fun getUsersList(): List<UserDto>
    suspend fun getUserDetail(userId: Int): UserDto
}