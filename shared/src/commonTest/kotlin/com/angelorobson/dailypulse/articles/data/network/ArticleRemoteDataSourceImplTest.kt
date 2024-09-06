package com.angelorobson.dailypulse.articles.data.network

import com.angelorobson.dailypulse.articles.data.network.responses.ArticleRawResponse
import com.angelorobson.dailypulse.articles.domain.network.ArticleRemoteDataSource
import com.angelorobson.dailypulse.articles.domain.network.api.ArticlesApi
import kotlinx.coroutines.runBlocking
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ArticleRemoteDataSourceImplTest : TestsWithMocks() {


    @Mock
    lateinit var articlesApi: ArticlesApi

    @Fake
    lateinit var response: ArticleRawResponse

    private val remoteDataSource: ArticleRemoteDataSource by withMocks {
        ArticleRemoteDataSourceImpl(articlesApi)
    }

    override fun setUpMocks() = injectMocks(mocker)


    @Test
    fun `fetchArticles should call fetchArticles from articlesApi`() = runBlocking {
        everySuspending { articlesApi.fetchArticles() } returns listOf(response)

        val list = remoteDataSource.fetchArticles()

        verifyWithSuspend { articlesApi.fetchArticles() }
        assertFalse(list.isEmpty())
        assertTrue(list.isNotEmpty())
    }

    @Test
    fun `fetchArticles should return an exception`() = runBlocking {
        val errorMsg = "Api error"
        everySuspending { articlesApi.fetchArticles() } runs { error(errorMsg) }


        val ex = assertFailsWith<IllegalStateException> {
            remoteDataSource.fetchArticles()
        }

        assertEquals(errorMsg, ex.message)
    }

}