package com.angelorobson.dailypulse.sources.domain.usecases.impl

import com.angelorobson.dailypulse.sources.domain.models.Source
import com.angelorobson.dailypulse.sources.domain.repositories.SourcesRepository
import com.angelorobson.dailypulse.sources.domain.usecases.SourcesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SourcesUseCaseImpl(
    private val repository: SourcesRepository,
) : SourcesUseCase {

    override suspend operator fun invoke(): Flow<List<Source>> = flow {
        val sourcesDb = repository.getLocalSources()
        if (sourcesDb.isEmpty()) {
            repository.clearLocalSources()
            val remoteSources = repository.fetchRemoteSources()
            repository.createSources(remoteSources)
            emit(remoteSources)
            return@flow
        }

        emit(sourcesDb)
    }
}