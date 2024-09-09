package com.angelorobson.dailypulse.articles.data.network.api

import com.angelorobson.dailypulse.TestUtils.getClient
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
                content = "{\n" +
                        "    \"status\": \"ok\",\n" +
                        "    \"totalResults\": 70,\n" +
                        "    \"articles\": [\n" +
                        "        {\n" +
                        "            \"source\": {\n" +
                        "                \"id\": null,\n" +
                        "                \"name\": \"Ambcrypto.com\"\n" +
                        "            },\n" +
                        "            \"author\": \"Muthoni Mary\",\n" +
                        "            \"title\": \"Crypto market rebounds on Yellen’s positive macro outlook – Gains ahead? - AMBCrypto News\",\n" +
                        "            \"description\": \"Buyers remain hesitant as they await the release of the US CPI data on 11th September\",\n" +
                        "            \"url\": \"https://ambcrypto.com/crypto-market-rebounds-on-yellens-positive-macro-outlook-gains-ahead/\",\n" +
                        "            \"urlToImage\": \"https://ambcrypto.com/wp-content/uploads/2024/09/News-Articles-FI-Editors-2024-09-08T133330.167-1000x600.jpg\",\n" +
                        "            \"publishedAt\": \"2024-09-08T12:04:02Z\",\n" +
                        "            \"content\": \"<ul><li>US Treasury Secretary Janet Yellen has said that the US economy is healthy and there are no signs of a recession.</li><li>The crypto market has recovered slightly, with Bitcoin reclaiming \$54… [+2759 chars]\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}",
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