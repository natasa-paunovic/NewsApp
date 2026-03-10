package com.android.newsapp.data.repository


import com.android.newsapp.data.mapper.toDomain
import com.android.newsapp.data.remote.api.ApiService
import com.android.newsapp.domain.model.NewsResponse
import com.android.newsapp.domain.repository.ArticleRepository
import com.android.newsapp.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException

class ArticleRepositoryImpl(

    private val api: ApiService

) : ArticleRepository {


    override suspend fun getNews(
        page: Int,
        pageSize: Int
    ): Resource<NewsResponse> {
        return try {

            val response = api.getNews(
                page = page,
                pageSize = pageSize,
                apiKey = BuildConfig.NEWS_API_KEY
            )

            Resource.Success(response.toDomain())

        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "HTTP error")

        } catch (e: IOException) {
            Resource.Error(e.localizedMessage ?: "Check internet connection")

        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Unexpected error")
        }

    }
}