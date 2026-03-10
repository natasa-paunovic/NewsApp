package com.android.newsapp.domain.repository


import com.android.newsapp.domain.model.NewsResponse
import com.android.newsapp.domain.util.Resource

interface ArticleRepository {

    suspend fun getNews(page: Int, pageSize: Int): Resource<NewsResponse>

}