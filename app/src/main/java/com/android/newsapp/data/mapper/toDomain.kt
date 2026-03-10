package com.android.newsapp.data.mapper

import com.android.newsapp.data.remote.dto.ArticleDto
import com.android.newsapp.data.remote.dto.NewsResponseDto
import com.android.newsapp.domain.model.Article
import com.android.newsapp.domain.model.NewsResponse
import java.security.MessageDigest

fun ArticleDto.toDomain(): Article {
    return Article(
        id = url.sha256(),
        title = title,
        author = author?.takeIf { it.isNotBlank() },
        sourceName = source.name.takeIf { it.isNotBlank() } ?: "Unknown",
        description = description?.takeIf { it.isNotBlank() },
        url = url,
        imageUrl = urlToImage?.takeIf { it.isNotBlank() },
        publishedAt = publishedAt
    )
}

fun NewsResponseDto.toDomain(): NewsResponse {
    return NewsResponse(
        status = status,
        totalResults = totalResults,
        articles = articles.map { it.toDomain() }
    )
}


fun String.sha256(): String {
    val bytes = MessageDigest
        .getInstance("SHA-256")
        .digest(this.toByteArray())

    return bytes.joinToString("") { "%02x".format(it) }
}