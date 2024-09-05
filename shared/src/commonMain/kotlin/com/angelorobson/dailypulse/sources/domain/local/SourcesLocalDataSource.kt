package com.angelorobson.dailypulse.sources.domain.local

import com.angelorobson.dailypulse.sources.data.network.responses.SourceRawResponse

interface SourcesLocalDataSource {

    fun getAllSources(): List<SourceRawResponse>

    fun clearSources()

    fun createSources(sources: List<SourceRawResponse>)
}