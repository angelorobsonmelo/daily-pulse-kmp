package com.angelorobson.dailypulse.articles.data.local

import com.angelorobson.dailypulse.articles.data.network.responses.ArticleRawResponse
import com.angelorobson.dailypulse.db.DailyPulseDatabase

class ArticlesDataSource(private val database: DailyPulseDatabase) {

    fun getAllArticles(): List<ArticleRawResponse> =
        database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRaw).executeAsList()

    fun insertArticles(articles: List<ArticleRawResponse>) {
        database.dailyPulseDatabaseQueries.transaction {
            articles.forEach { articleRaw ->
                insertArticle(articleRaw)
            }
        }
    }

    fun clearArticles() =
        database.dailyPulseDatabaseQueries.removeAllArticles()

    private fun insertArticle(articleRaw: ArticleRawResponse) {
        database.dailyPulseDatabaseQueries.insertArticle(
            articleRaw.title,
            articleRaw.desc,
            articleRaw.date,
            articleRaw.imageUrl
        )
    }

    private fun mapToArticleRaw(
        title: String,
        desc: String?,
        date: String,
        url: String?
    ): ArticleRawResponse =
        ArticleRawResponse(
            title,
            desc,
            date,
            url
        )
}