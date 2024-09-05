package com.angelorobson.dailypulse.sources.domain.usecases

import com.angelorobson.dailypulse.sources.domain.models.Source
import com.angelorobson.dailypulse.sources.domain.repositories.SourcesRepository

class SourcesUseCase(
    private val repository: SourcesRepository,
) {

     suspend operator fun invoke(): List<Source> {
        val sourcesDb = repository.getLocalSources()
        if (sourcesDb.isEmpty()) {
            repository.clearLocalSources()
            val remoteSources = repository.fetchRemoteSources()
            repository.createSources(remoteSources)
            return remoteSources
        }
        return sourcesDb
    }
}