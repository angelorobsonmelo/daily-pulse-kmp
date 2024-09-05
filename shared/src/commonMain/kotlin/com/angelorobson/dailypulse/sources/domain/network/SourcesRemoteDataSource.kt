package com.angelorobson.dailypulse.sources.domain.network

import com.angelorobson.dailypulse.sources.data.network.responses.SourceRawResponse

interface SourcesRemoteDataSource {

    suspend fun fetchSources(): List<SourceRawResponse>
}