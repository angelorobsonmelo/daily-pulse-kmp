package com.angelorobson.dailypulse.articles.data.repositories

import com.angelorobson.dailypulse.articles.data.local.ArticlesDataSource
import com.angelorobson.dailypulse.articles.data.network.responses.ArticleRawResponse
import com.angelorobson.dailypulse.articles.data.network.ArticleService

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val service: ArticleService
) {

    suspend fun getArticles(forceFetch: Boolean): List<ArticleRawResponse> {
        if (forceFetch) {
            dataSource.clearArticles()
            return fetchArticles()
        }

        val articlesDb = dataSource.getAllArticles()
        println("Got ${articlesDb.size} from the database!!")

        if (articlesDb.isEmpty()) {
            return fetchArticles()
        }

        return articlesDb
    }

    private suspend fun fetchArticles(): List<ArticleRawResponse> {
        val fetchedArticles = service.fetchArticles()
        dataSource.insertArticles(fetchedArticles)
        return fetchedArticles
    }
}