package com.angelorobson.dailypulse.sources.data.repositories

import com.angelorobson.dailypulse.sources.data.local.mapper.SourceEntityToDomainMapper
import com.angelorobson.dailypulse.sources.data.network.mapper.SourceResponseToDomainMapper
import com.angelorobson.dailypulse.sources.domain.local.SourcesLocalDataSource
import com.angelorobson.dailypulse.sources.domain.models.Source
import com.angelorobson.dailypulse.sources.domain.network.SourcesRemoteDataSource
import com.angelorobson.dailypulse.sources.domain.repositories.SourcesRepository

class SourcesRepositoryImpl(
    private val dataSource: SourcesLocalDataSource,
    private val service: SourcesRemoteDataSource,
    private val mapper: SourceResponseToDomainMapper,
    private val sourceEntityMapper: SourceEntityToDomainMapper
) : SourcesRepository {


    override suspend fun getLocalSources(): List<Source> {
        return dataSource.getAllSources().map {
            sourceEntityMapper.mapSources(it)
        }
    }

    override suspend fun fetchRemoteSources(): List<Source> {
        val fetchedSources = service.fetchSources()
        return fetchedSources.map { mapper.responseToModel(it) }
    }

    override suspend fun createSources(sources: List<Source>) {
        dataSource.createSources(sources)
    }

    override suspend fun clearLocalSources() {
        dataSource.clearSources()
    }

}