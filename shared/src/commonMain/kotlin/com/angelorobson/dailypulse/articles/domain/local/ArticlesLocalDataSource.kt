package com.angelorobson.dailypulse.articles.domain.local

import com.angelorobson.dailypulse.articles.domain.models.Article
import com.angelorobson.dailypulse.db.ArticleEntity

interface ArticlesLocalDataSource {

    fun getAllArticles(): List<ArticleEntity>

    fun insertArticles(articles: List<Article>)

    fun clearArticles()
}