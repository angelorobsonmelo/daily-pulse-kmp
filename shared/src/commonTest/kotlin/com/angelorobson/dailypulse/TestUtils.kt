package com.angelorobson.dailypulse

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

data object TestUtils {

    fun getClient(engine: MockEngine): HttpClient {
        return HttpClient(engine) {
            expectSuccess = true
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }

                level = LogLevel.BODY
            }
            install(HttpTimeout) {
                val timeout = 30000L
                connectTimeoutMillis = timeout
                requestTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }
        }
    }

    fun newsJsonResponseBody(): String {
       return "{\n" +
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
                "}"
    }

    fun sourcesJsonResponseBody(): String {
        return "{\n" +
                "    \"status\": \"ok\",\n" +
                "    \"sources\": [\n" +
                "        {\n" +
                "            \"id\": \"abc-news\",\n" +
                "            \"name\": \"ABC News\",\n" +
                "            \"description\": \"Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.\",\n" +
                "            \"url\": \"https://abcnews.go.com\",\n" +
                "            \"category\": \"general\",\n" +
                "            \"language\": \"en\",\n" +
                "            \"country\": \"us\"\n" +
                "        }\n" +
                "    ]\n" +
                "}"
    }
}