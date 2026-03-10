package com.android.newsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
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
                    navController.navigate("article_details/${article.id}")
                }
            )
        }

        composable(
            route = "article_details/{articleId}",
            arguments = listOf(navArgument("articleId") { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink { uriPattern = "myapp://article/{articleId}" })
        ) {backStackEntry ->

            val articleId = backStackEntry.arguments?.getString("articleId")

            LaunchedEffect(articleId) {
                articleId?.let { viewModel.selectArticle(it) }

                if (viewModel.selectedArticle.value == null) {
                    articleId?.let { viewModel.fetchArticleById(it) }
                }
            }


            ArticleDetailScreen(
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()

                }
            )
        }
    }
}