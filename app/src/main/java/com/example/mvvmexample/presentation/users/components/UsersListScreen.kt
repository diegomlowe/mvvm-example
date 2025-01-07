package com.example.mvvmexample.presentation.users.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mvvmexample.domain.model.User
import com.example.mvvmexample.presentation.UiStateHandler
import com.example.mvvmexample.presentation.UserMapLocationNavScreen
import com.example.mvvmexample.presentation.users.UsersListViewModel
import androidx.compose.foundation.lazy.items


@Composable
fun UsersListScreen(
    navController: NavController,
    usersViewModel: UsersListViewModel = hiltViewModel()
) {
    val uiState = usersViewModel.uiState.collectAsState().value

    UiStateHandler(
        isLoading = uiState.isLoading,
        error = uiState.error
    ) {
        UsersLazyList(uiState.data, navController)
    }
}

@Composable
fun UsersLazyList(usersList: List<User>?, navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(usersList ?: emptyList()) { user ->
            UserListItem(
                user = user,
                onItemClick = {
                    navController.navigate(UserMapLocationNavScreen(userId = user.id))
                }
            )
        }
    }
}