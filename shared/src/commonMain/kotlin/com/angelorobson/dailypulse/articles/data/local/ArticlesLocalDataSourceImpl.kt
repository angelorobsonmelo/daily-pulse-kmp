package com.angelorobson.dailypulse.articles.data.local

import com.angelorobson.dailypulse.articles.data.mappers.ArticlesEntityMapper
import com.angelorobson.dailypulse.articles.data.network.responses.ArticleRawResponse
import com.angelorobson.dailypulse.articles.domain.local.ArticlesLocalDataSource
import com.angelorobson.dailypulse.db.DailyPulseDatabase

class ArticlesLocalDataSourceImpl(
    private val database: DailyPulseDatabase,
    private val articlesEntityMapper: ArticlesEntityMapper
) : ArticlesLocalDataSource {

    override fun getAllArticles(): List<ArticleRawResponse> =
        database.dailyPulseDatabaseQueries.selectAllArticles()
            .executeAsList()
            .map {
                articlesEntityMapper.mapToArticleRaw(
                    it.title,
                    it.desc,
                    it.date,
                    it.imageUrl
                )
            }

    override fun insertArticles(articles: List<ArticleRawResponse>) {
        database.dailyPulseDatabaseQueries.transaction {
            articles.forEach { articleRaw ->
                insertArticle(articleRaw)
            }
        }
    }

    override fun clearArticles() =
        database.dailyPulseDatabaseQueries.removeAllArticles()

    private fun insertArticle(articleRaw: ArticleRawResponse) {
        database.dailyPulseDatabaseQueries.insertArticle(
            articleRaw.title,
            articleRaw.desc,
            articleRaw.date,
            articleRaw.imageUrl
        )
    }
}