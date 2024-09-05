package com.angelorobson.dailypulse.sources.domain

import com.angelorobson.dailypulse.sources.data.network.responses.SourceRaw
import com.angelorobson.dailypulse.sources.data.repositories.SourcesRepository
import com.angelorobson.dailypulse.sources.domain.models.Source

class SourcesUseCase(private val repo: SourcesRepository) {

    suspend fun getSources(): List<Source> {
        val sourcesRaw = repo.getAllSources()

        return mapSources(sourcesRaw)
    }

    private fun mapSources(sourcesRaw: List<SourceRaw>): List<Source> = sourcesRaw.map { raw ->
        Source(
            raw.id,
            raw.name,
            raw.desc,
            mapOrigin(raw),
        )
    }

    private fun mapOrigin(raw: SourceRaw) = "${raw.country} - ${raw.language}"
}