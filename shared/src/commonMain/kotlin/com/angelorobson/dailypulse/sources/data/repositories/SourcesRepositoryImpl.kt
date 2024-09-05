package com.angelorobson.dailypulse.sources.data.repositories

import com.angelorobson.dailypulse.sources.data.network.responses.SourceRawResponse
import com.angelorobson.dailypulse.sources.domain.local.SourcesLocalDataSource
import com.angelorobson.dailypulse.sources.domain.network.SourcesRemoteDataSource
import com.angelorobson.dailypulse.sources.domain.repositories.SourcesRepository

class SourcesRepositoryImpl(
    private val dataSource: SourcesLocalDataSource,
    private val service: SourcesRemoteDataSource
) : SourcesRepository {

    override suspend fun getAllSources(): List<SourceRawResponse> {
        val sourcesDb = dataSource.getAllSources()
        if (sourcesDb.isEmpty()) {
            dataSource.clearSources()
            val fetchedSources = service.fetchSources()
            dataSource.createSources(fetchedSources)
            return fetchedSources
        }
        return sourcesDb
    }
}