package com.android.newsapp.data.remote.api

import com.android.newsapp.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "us",
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 10,
        @Query("apiKey") apiKey: String,
    ): NewsResponseDto

}