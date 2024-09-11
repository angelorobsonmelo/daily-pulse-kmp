package com.angelorobson.dailypulse.articles.data.network.api

import com.angelorobson.dailypulse.BuildKonfig.API_KEY
import com.angelorobson.dailypulse.articles.data.network.HttpRoutes.GET_TOP_HEADLINES
import com.angelorobson.dailypulse.articles.data.network.responses.ArticleRawResponse
import com.angelorobson.dailypulse.articles.data.network.responses.ArticlesResponse
import com.angelorobson.dailypulse.articles.domain.network.api.ArticlesApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesApiImpl(
    private val httpClient: HttpClient
) : ArticlesApi {

    override suspend fun fetchArticles(): List<ArticleRawResponse> {
        val topHeadlinesUrl = GET_TOP_HEADLINES.replace("{API_KEY}", API_KEY)

        val response: ArticlesResponse =
            httpClient.get(topHeadlinesUrl).body()
        return response.articles
    }
}