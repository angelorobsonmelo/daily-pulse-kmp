package com.angelorobson.dailypulse.articles.domain.usecases

import com.angelorobson.dailypulse.articles.domain.models.Article
import com.angelorobson.dailypulse.articles.domain.repositories.ArticlesRepository

class ArticleUseCase(
    private val repository: ArticlesRepository,
) {

    suspend operator fun invoke(forceFetch: Boolean): List<Article> {
        if (forceFetch) {
            repository.clearLocalArticles()
            val fetchedArticles = repository.fetchRemoteArticles()
            repository.createArticles(fetchedArticles)
            return fetchedArticles
        }

        val localArticles = repository.getLocalArticles()

        if (localArticles.isEmpty()) {
            val fetchedArticles = repository.fetchRemoteArticles()
            repository.createArticles(fetchedArticles)
            return fetchedArticles
        }

        return localArticles
    }
}