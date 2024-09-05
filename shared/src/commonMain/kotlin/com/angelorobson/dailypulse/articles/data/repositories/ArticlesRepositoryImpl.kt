package com.angelorobson.dailypulse.articles.data.repositories

import com.angelorobson.dailypulse.articles.data.local.mappers.ArticleEntityToDomainMapper
import com.angelorobson.dailypulse.articles.data.network.mappers.ArticleResponseToDomainMapper
import com.angelorobson.dailypulse.articles.domain.local.ArticlesLocalDataSource
import com.angelorobson.dailypulse.articles.domain.models.Article
import com.angelorobson.dailypulse.articles.domain.network.ArticleRemoteDataSource
import com.angelorobson.dailypulse.articles.domain.repositories.ArticlesRepository

class ArticlesRepositoryImpl(
    private val remoteDataSource: ArticleRemoteDataSource,
    private val localDataSource: ArticlesLocalDataSource,
    private val articlesMapper: ArticleEntityToDomainMapper,
    private val articleResponseToDomainMapper: ArticleResponseToDomainMapper
) : ArticlesRepository {

    override suspend fun getLocalArticles(): List<Article> {
        return localDataSource.getAllArticles().map {
            articlesMapper.mapArticles(it)
        }
    }

    override suspend fun fetchRemoteArticles(): List<Article> {
        return remoteDataSource.fetchArticles().map {
            articleResponseToDomainMapper.mapArticles(it)
        }
    }

    override suspend fun createArticles(articles: List<Article>) {
        localDataSource.insertArticles(articles)
    }

    override suspend fun clearLocalArticles() {
        localDataSource.clearArticles()
    }

}