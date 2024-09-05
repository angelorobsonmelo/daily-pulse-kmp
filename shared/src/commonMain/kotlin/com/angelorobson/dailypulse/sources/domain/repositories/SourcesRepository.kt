package com.angelorobson.dailypulse.sources.domain.repositories

import com.angelorobson.dailypulse.sources.data.network.responses.SourceRawResponse

interface SourcesRepository {

    suspend fun getAllSources(): List<SourceRawResponse>
}