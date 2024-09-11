package com.angelorobson.dailypulse.sources.data.network.api

import com.angelorobson.dailypulse.BuildKonfig.API_KEY
import com.angelorobson.dailypulse.articles.data.network.HttpRoutes.GET_SOURCES
import com.angelorobson.dailypulse.sources.data.network.responses.SourceRawResponse
import com.angelorobson.dailypulse.sources.data.network.responses.SourcesResponse
import com.angelorobson.dailypulse.sources.domain.network.api.SourceApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourcesApiImpl(private val httpClient: HttpClient) : SourceApi {

    override suspend fun fetchSources(): List<SourceRawResponse> {
        val sourcesUrl = GET_SOURCES.replace("{API_KEY}", API_KEY)

        val response: SourcesResponse =
            httpClient.get(sourcesUrl).body()
        return response.sources
    }
}