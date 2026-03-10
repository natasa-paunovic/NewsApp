package com.android.newsapp.domain.model

data class Article(
    /** Stable identifier derived from the article URL (SHA-256 hash). */
    val id: String,
    val title: String,
    val author: String?,
    val sourceName: String,
    val description: String?,
    val url: String,
    val imageUrl: String?,
    val publishedAt: String,
)
