package com.angelorobson.dailypulse.sources.domain.repositories

import com.angelorobson.dailypulse.sources.domain.models.Source

interface SourcesRepository {

    suspend fun getLocalSources(): List<Source>
    suspend fun fetchRemoteSources(): List<Source>
    suspend fun createSources(sources: List<Source>)
    suspend fun clearLocalSources()
}