package com.angelorobson.dailypulse.sources.domain.usecases

import com.angelorobson.dailypulse.sources.domain.models.Source
import com.angelorobson.dailypulse.sources.domain.repositories.SourcesRepository
import kotlinx.coroutines.flow.Flow

interface SourcesUseCase {

    suspend operator fun invoke(): Flow<List<Source>>
}