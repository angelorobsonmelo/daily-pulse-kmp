package com.angelorobson.dailypulse.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticleService(
    private val httpClient: HttpClient
) {

    private val country = "us"
    private val category = "business"
    private val apiKey = "2317a629fa1e4df4a42db58d2995f47f"

    suspend fun fetchArticles(): List<ArticleRaw> {
        val response: ArticlesResponse = httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey").body()
        return response.articles
    }
}