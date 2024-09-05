package com.angelorobson.dailypulse.sources.data.repositories

import com.angelorobson.dailypulse.sources.data.local.SourcesDataSource
import com.angelorobson.dailypulse.sources.data.network.SourcesService
import com.angelorobson.dailypulse.sources.data.network.responses.SourceRaw

class SourcesRepository(
    private val dataSource: SourcesDataSource,
    private val service: SourcesService
) {

    suspend fun getAllSources(): List<SourceRaw> {
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