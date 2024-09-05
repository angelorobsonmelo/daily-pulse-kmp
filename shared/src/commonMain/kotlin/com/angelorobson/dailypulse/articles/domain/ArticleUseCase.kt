package com.angelorobson.dailypulse.articles.domain

import com.angelorobson.dailypulse.articles.domain.mappers.ArticlesMapper
import com.angelorobson.dailypulse.articles.domain.models.Article
import com.angelorobson.dailypulse.articles.domain.repositories.ArticlesRepository

class ArticleUseCase(
    private val repository: ArticlesRepository,
    private val articlesMapper: ArticlesMapper
) {

    suspend fun getArticles(forceFetch: Boolean): List<Article> {
        val articlesRaw = repository.getArticles(forceFetch)
        return articlesMapper.mapArticles(articlesRaw)
    }

}