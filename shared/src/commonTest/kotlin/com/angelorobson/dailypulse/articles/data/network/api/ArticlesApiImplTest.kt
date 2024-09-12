package com.angelorobson.dailypulse.articles.data.network.api

import com.angelorobson.dailypulse.TestUtils.getClient
import com.angelorobson.dailypulse.TestUtils.newsJsonResponseBody
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class ArticlesApiImplTest {


    @Test
    fun `fetchArticles should call fetchArticles from articlesApi`() = runTest {
        val mockEngine = MockEngine {
            respond(
                content = newsJsonResponseBody(),
                headers = headersOf(
                    HttpHeaders.ContentType,
                    ContentType.Application.Json.toString()
                )
            )
        }

        val client = getClient(engine = mockEngine)

        val articlesApi = ArticlesApiImpl(client)

        val articles = articlesApi.fetchArticles()

        assertFalse(articles.isEmpty())
        assertTrue(articles.isNotEmpty())
    }


}