package com.android.newsapp.di


import com.android.newsapp.data.remote.api.ApiService
import com.android.newsapp.data.repository.ArticleRepositoryImpl
import com.android.newsapp.domain.repository.ArticleRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.android.newsapp.BuildConfig


val appModule = module {


    // Provide ArticleRepository
    single<ArticleRepository> {
        ArticleRepositoryImpl(get())
    }



    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    single {
        Retrofit.Builder().client(okHttpClient).baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }


}

