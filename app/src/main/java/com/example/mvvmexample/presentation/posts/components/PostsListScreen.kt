package com.example.mvvmexample.presentation.posts.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mvvmexample.domain.model.Post
import com.example.mvvmexample.presentation.PostDetailNavScreen
import com.example.mvvmexample.presentation.UiStateHandler
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import com.example.mvvmexample.presentation.posts.PostsListViewModel


@Composable
fun PostsListScreen(
    navController: NavController,
    postsListViewModel: PostsListViewModel = hiltViewModel()
) {

    val uiState = postsListViewModel.uiState.collectAsState().value

    UiStateHandler(
        isLoading = uiState.isLoading,
        error = uiState.error
    ) {
        PostsLazyList(uiState.data, navController, postsListViewModel)
    }
}

@Composable
fun PostsLazyList(
    postsList: List<Post>?,
    navController: NavController,
    postsListViewModel: PostsListViewModel
) {
    Column {
        TextInputFilter(
            onTextChange = {
                postsListViewModel.filterPosts(it)
            }
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(postsList ?: emptyList()) { post ->
                PostListItem(
                    post = post,
                    onItemClick = {
                        navController.navigate(PostDetailNavScreen(postId = post.id))
                    }
                )
            }
        }
    }
}

@Composable
fun TextInputFilter(
    label: String = "Search",
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val textState = remember { mutableStateOf("") }
    OutlinedTextField(
        value = textState.value,
        onValueChange = {
            textState.value = it
            onTextChange(it)
        },
        label = { Text(text = label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}