package com.angelorobson.dailypulse.articles.data.repositories

import com.angelorobson.dailypulse.articles.data.network.responses.ArticleRawResponse
import com.angelorobson.dailypulse.articles.domain.local.ArticlesLocalDataSource
import com.angelorobson.dailypulse.articles.domain.network.ArticleRemoteDataSource
import com.angelorobson.dailypulse.articles.domain.repositories.ArticlesRepository

class ArticlesRepositoryImpl(
    private val remoteDataSource: ArticleRemoteDataSource,
    private val localDataSource: ArticlesLocalDataSource
) : ArticlesRepository {

    override suspend fun getArticles(forceFetch: Boolean): List<ArticleRawResponse> {
        if (forceFetch) {
            localDataSource.clearArticles()
            return fetchArticles()
        }

        val articlesDb = localDataSource.getAllArticles()
        println("Got ${articlesDb.size} from the database!!")

        if (articlesDb.isEmpty()) {
            return fetchArticles()
        }

        return articlesDb
    }

    private suspend fun fetchArticles(): List<ArticleRawResponse> {
        val fetchedArticles = remoteDataSource.fetchArticles()
        localDataSource.insertArticles(fetchedArticles)
        return fetchedArticles
    }
}