package com.angelorobson.dailypulse.sources.data.network.api

import com.angelorobson.dailypulse.sources.data.network.responses.SourceRawResponse
import com.angelorobson.dailypulse.sources.data.network.responses.SourcesResponse
import com.angelorobson.dailypulse.sources.domain.network.api.SourceApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourcesApiImpl(private val httpClient: HttpClient) : SourceApi {
    private val apiKey = "f67ace1b27b24ce4b95c7f71fde88920"

    override suspend fun fetchSources(): List<SourceRawResponse> {

        val response: SourcesResponse =
            httpClient.get("https://newsapi.org/v2/top-headlines/sources?apiKey=$apiKey").body()
        return response.sources
    }
}