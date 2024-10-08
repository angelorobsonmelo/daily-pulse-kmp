package com.angelorobson.dailypulse.articles.domain.usecases.impl

import com.angelorobson.dailypulse.articles.domain.models.Article
import com.angelorobson.dailypulse.articles.domain.repositories.ArticlesRepository
import com.angelorobson.dailypulse.articles.domain.usecases.ArticleUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticleUseCaseImpl(
    private val repository: ArticlesRepository,
) : ArticleUseCase {

    override suspend operator fun invoke(forceFetch: Boolean): Flow<List<Article>> = flow {
        if (forceFetch) {
            repository.clearLocalArticles()
            val fetchedArticles = repository.fetchRemoteArticles()
            repository.createArticles(fetchedArticles)
            emit(fetchedArticles)
            return@flow
        }

        val localArticles = repository.getLocalArticles()

        if (localArticles.isEmpty()) {
            val fetchedArticles = repository.fetchRemoteArticles()
            repository.createArticles(fetchedArticles)
            emit(fetchedArticles)
            return@flow
        }

        emit(localArticles)
    }
}