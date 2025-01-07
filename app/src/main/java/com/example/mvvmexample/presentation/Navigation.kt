package com.example.mvvmexample.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.mvvmexample.presentation.posts.components.PostDetailScreen
import com.example.mvvmexample.presentation.users.components.UserMapLocationScreen
import kotlinx.serialization.Serializable

@Serializable
object MainTabsNavScreen

@Serializable
data class PostDetailNavScreen(
    val postId: Int
)

@Serializable
data class UserMapLocationNavScreen(
    val userId: Int
)

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MainTabsNavScreen
    ) {
        composable<MainTabsNavScreen> {
            MainTabsScreen(navController)
        }
        composable<PostDetailNavScreen> {
            PostDetailScreen(postId = it.toRoute<PostDetailNavScreen>().postId)
        }
        composable<UserMapLocationNavScreen> {
            UserMapLocationScreen(userId = it.toRoute<UserMapLocationNavScreen>().userId)
        }
    }
}