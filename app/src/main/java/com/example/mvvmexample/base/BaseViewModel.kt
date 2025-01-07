package com.example.mvvmexample.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<T> : ViewModel() {

    private val _uiState = MutableStateFlow(UiState<T>())
    val uiState: StateFlow<UiState<T>> = _uiState

    protected fun collectFlow(flow: Flow<Resource<T>>) {
        viewModelScope.launch {
            flow.flowOn(Dispatchers.IO)
                .collect { result ->
                    handleResult(result)
                }
        }
    }

    private fun handleResult(result: Resource<T>) {
        when (result) {
            is Resource.Success -> handleSuccess(result)
            is Resource.Failure -> updateUiState(error = result.message)
            is Resource.Loading -> updateUiState(isLoading = true)
        }
    }

    protected fun updateUiState(
        isLoading: Boolean = false,
        data: T? = null,
        error: String = ""
    ) {
        _uiState.update {
            it.copy(isLoading = isLoading, data = data, error = error)
        }
    }

    protected open fun handleSuccess(result: Resource.Success<T>){
        updateUiState(data = result.data)
    }
}

data class UiState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String = ""
)