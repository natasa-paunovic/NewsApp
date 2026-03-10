package com.android.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.newsapp.presentation.article.detail.ArticleDetailScreen
import com.android.newsapp.presentation.article.list.ArticleListScreen
import com.android.newsapp.presentation.viewmodel.NewsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsNavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: NewsViewModel = koinViewModel()
) {
    NavHost(navController = navController, startDestination = "article_list") {

        composable("article_list") {
            ArticleListScreen(
                viewModel = viewModel,
                onArticleClick = { article ->
                    viewModel.selectArticle(article.id)
                    navController.navigate("article_details")
                }
            )
        }

        composable("article_details") {
            ArticleDetailScreen(
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()

                }
            )
        }
    }
}