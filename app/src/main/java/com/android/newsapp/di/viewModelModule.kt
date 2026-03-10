package com.android.newsapp.di

import com.android.newsapp.presentation.viewmodel.NewsViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val viewModelModule = module {
    viewModel {
        NewsViewModel(get())
    }

}