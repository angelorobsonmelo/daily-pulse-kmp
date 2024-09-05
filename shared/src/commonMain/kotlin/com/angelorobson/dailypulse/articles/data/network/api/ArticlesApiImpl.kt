package com.angelorobson.dailypulse.articles.data.network.api

import com.angelorobson.dailypulse.articles.data.network.responses.ArticleRawResponse
import com.angelorobson.dailypulse.articles.data.network.responses.ArticlesResponse
import com.angelorobson.dailypulse.articles.domain.network.api.ArticlesApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesApiImpl(
    private val httpClient: HttpClient
): ArticlesApi {

    private val country = "us"
    private val category = "business"
    private val apiKey = "2317a629fa1e4df4a42db58d2995f47f"

    override suspend fun fetchArticles(): List<ArticleRawResponse> {
        val response: ArticlesResponse = httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey").body()
        return response.articles
    }
}