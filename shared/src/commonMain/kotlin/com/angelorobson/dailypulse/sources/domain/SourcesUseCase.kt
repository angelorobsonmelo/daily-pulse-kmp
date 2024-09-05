package com.angelorobson.dailypulse.sources.domain

import com.angelorobson.dailypulse.sources.domain.mappers.SourceMapper
import com.angelorobson.dailypulse.sources.domain.models.Source
import com.angelorobson.dailypulse.sources.domain.repositories.SourcesRepository

class SourcesUseCase(
    private val repo: SourcesRepository,
    private val mapper: SourceMapper
) {

    suspend fun getSources(): List<Source> {
        val sourcesRaw = repo.getAllSources()

        return mapper.mapSources(sourcesRaw)
    }
}