package com.example.mvvmexample.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mvvmexample.presentation.posts.components.PostsListScreen
import com.example.mvvmexample.presentation.users.components.UsersListScreen


@Composable
fun MainTabsScreen(
    navController: NavController
) {
    val tabItems = listOf(
        TabItem("Posts"),
        TabItem("Users")
    )

    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, tabItem ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = tabItem.text) }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> PostsListScreen(navController)
            1 -> UsersListScreen(navController)
        }
    }
}

data class TabItem(
    val text: String
)