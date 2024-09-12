package com.angelorobson.dailypulse.articles.data.local

import com.angelorobson.dailypulse.articles.domain.local.ArticlesLocalDataSource
import com.angelorobson.dailypulse.articles.domain.models.Article
import com.angelorobson.dailypulse.builders.ArticleBuilder
import com.angelorobson.dailypulse.db.DailyPulseDatabase
import com.angelorobson.dailypulse.testDbConnection
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ArticleLocalDataSourceImplTest {

    private lateinit var dailyPulseDatabase: DailyPulseDatabase
    private lateinit var articleLocalDataSource: ArticlesLocalDataSource

    private val article = ArticleBuilder().build()

    @BeforeTest
    fun setUp() {
        val driver = testDbConnection()
        dailyPulseDatabase = DailyPulseDatabase(driver)
        articleLocalDataSource = ArticlesLocalDataSourceImpl(dailyPulseDatabase)

        articleLocalDataSource.insertArticles(listOf(article))
    }

    @Test
    fun shouldReturnPlatformInstanceNotNull() {
        assertNotNull(dailyPulseDatabase)
    }

    @Test
    fun `getAllArticles should return list of entities`() {
        val articles = articleLocalDataSource.getAllArticles()
        assertNotNull(articles)
    }

    @Test
    fun `clearArticles should return return an empty list`() {
        articleLocalDataSource.clearArticles()
        val articles = articleLocalDataSource.getAllArticles()
        assertTrue(articles.isEmpty())
    }

}