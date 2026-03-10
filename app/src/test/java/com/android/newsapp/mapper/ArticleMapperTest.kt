package com.android.newsapp.mapper

import com.android.newsapp.data.mapper.sha256
import com.android.newsapp.data.mapper.toDomain
import com.android.newsapp.data.remote.dto.ArticleDto
import com.android.newsapp.data.remote.dto.SourceDto
import org.junit.Assert
import org.junit.Test

class ArticleMapperTest {

    @Test
    fun `ArticleDto maps correctly to Article`() {
        val dto = ArticleDto(
            source = SourceDto(id = null, name = "CBS Sports"),
            author = "John Breech",
            title = "NFL free agency 2026 live updates",
            description = "The NFL free agency starts...",
            url = "https://www.cbssports.com/nfl/news/article123",
            urlToImage = "https://example.com/image.png",
            publishedAt = "2026-03-09T16:35:42Z",
            content = "Some content here..."
        )

        val domain = dto.toDomain()

        Assert.assertEquals(dto.url.sha256(), domain.id)
        Assert.assertEquals(dto.title, domain.title)
        Assert.assertEquals(dto.author, domain.author)
        Assert.assertEquals(dto.source.name, domain.sourceName)
        Assert.assertEquals(dto.description, domain.description)
        Assert.assertEquals(dto.url, domain.url)
        Assert.assertEquals(dto.urlToImage, domain.imageUrl)
        Assert.assertEquals(dto.publishedAt, domain.publishedAt)
    }

    @Test
    fun `ArticleDto maps null author, empty source name and empty imageUrl correctly`() {
        val dto = ArticleDto(
            source = SourceDto(id = null, name = ""),
            author = "",
            title = "Title",
            description = null,
            url = "https://example.com/article",
            urlToImage = "",
            publishedAt = "2026-03-09T00:00:00Z",
            content = null
        )

        val domain = dto.toDomain()

        Assert.assertNull(domain.author)
        Assert.assertEquals("Unknown", domain.sourceName)
        Assert.assertNull(domain.description)
        Assert.assertNull(domain.imageUrl)
    }
}