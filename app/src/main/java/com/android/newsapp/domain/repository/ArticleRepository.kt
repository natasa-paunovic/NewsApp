package com.android.newsapp.domain.repository

import com.android.newsapp.domain.model.Article

interface ArticleRepository {

    suspend fun getNews(page: Int, pageSize: Int): Result<List<Article>>

}