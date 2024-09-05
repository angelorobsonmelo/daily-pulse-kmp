package com.angelorobson.dailypulse.sources.data.network

import com.angelorobson.dailypulse.sources.data.network.responses.SourceRawResponse
import com.angelorobson.dailypulse.sources.data.network.responses.SourcesResponse
import com.angelorobson.dailypulse.sources.domain.network.SourcesRemoteDataSource
import com.angelorobson.dailypulse.sources.domain.network.api.SourceApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourcesRemoteDataSourceImpl(private val sourceApi: SourceApi) : SourcesRemoteDataSource {

    override suspend fun fetchSources(): List<SourceRawResponse> = sourceApi.fetchSources()
}