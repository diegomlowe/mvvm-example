package com.example.mvvmexample.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseRemoteUseCase<T> {
    protected fun fetchData(
        dataFetch: suspend () -> T
    ): Flow<Resource<T>> = flow {
        emit(Resource.Loading())
        try {
            val data = dataFetch()
            emit(Resource.Success(data))
        } catch (e: NetworkException) {
            emit(Resource.Failure(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}