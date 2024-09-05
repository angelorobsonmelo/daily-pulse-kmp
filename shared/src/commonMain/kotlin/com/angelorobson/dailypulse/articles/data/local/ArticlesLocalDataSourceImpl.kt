package com.angelorobson.dailypulse.articles.data.local

import com.angelorobson.dailypulse.articles.domain.local.ArticlesLocalDataSource
import com.angelorobson.dailypulse.articles.domain.models.Article
import com.angelorobson.dailypulse.db.ArticleEntity
import com.angelorobson.dailypulse.db.DailyPulseDatabase

class ArticlesLocalDataSourceImpl(
    private val database: DailyPulseDatabase
) : ArticlesLocalDataSource {

    override fun getAllArticles(): List<ArticleEntity> = database
        .dailyPulseDatabaseQueries
        .selectAllArticles()
        .executeAsList()

    override fun insertArticles(articles: List<Article>) {
        database.dailyPulseDatabaseQueries.transaction {
            articles.forEach { articleRaw ->
                insertArticle(articleRaw)
            }
        }
    }

    override fun clearArticles() =
        database.dailyPulseDatabaseQueries.removeAllArticles()

    private fun insertArticle(article: Article) {
        database.dailyPulseDatabaseQueries.insertArticle(
            article.title,
            article.desc,
            article.date,
            article.imageUrl
        )
    }
}