package com.angelorobson.dailypulse.sources.domain.network.api

import com.angelorobson.dailypulse.sources.data.network.responses.SourceRawResponse

interface SourceApi {

    suspend fun fetchSources(): List<SourceRawResponse>
}