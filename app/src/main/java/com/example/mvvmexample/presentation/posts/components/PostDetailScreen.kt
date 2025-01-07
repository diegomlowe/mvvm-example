package com.example.mvvmexample.presentation.posts.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvmexample.domain.model.Post
import com.example.mvvmexample.presentation.UiStateHandler
import com.example.mvvmexample.presentation.posts.PostDetailViewModel


@Composable
fun PostDetailScreen(
    postDetailViewModel: PostDetailViewModel = hiltViewModel(),
    postId: Int
) {

    postDetailViewModel.getPostDetail(postId = postId)
    val uiState = postDetailViewModel.uiState.collectAsState().value

    UiStateHandler(
        isLoading = uiState.isLoading,
        error = uiState.error
    ) {
        PostDetail(uiState.data)
    }
}

@Composable
fun PostDetail(post: Post?) {
    post?.let {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            item {
                Text(
                    text = "${it.id}. ${it.slug}",
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = it.title,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = it.content,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify,
                    overflow = TextOverflow.Clip
                )
            }
        }
    }
}