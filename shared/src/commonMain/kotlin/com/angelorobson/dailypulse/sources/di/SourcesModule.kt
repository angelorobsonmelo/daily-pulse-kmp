package com.angelorobson.dailypulse.sources.di

import com.angelorobson.dailypulse.sources.data.local.SourceEntityMapper
import com.angelorobson.dailypulse.sources.data.local.SourcesLocalDataSourceImpl
import com.angelorobson.dailypulse.sources.data.network.SourcesRemoteDataSourceImpl
import com.angelorobson.dailypulse.sources.data.network.api.SourcesApiImpl
import com.angelorobson.dailypulse.sources.data.repositories.SourcesRepositoryImpl
import com.angelorobson.dailypulse.sources.domain.SourcesUseCase
import com.angelorobson.dailypulse.sources.domain.local.SourcesLocalDataSource
import com.angelorobson.dailypulse.sources.domain.mappers.SourceMapper
import com.angelorobson.dailypulse.sources.domain.network.SourcesRemoteDataSource
import com.angelorobson.dailypulse.sources.domain.network.api.SourceApi
import com.angelorobson.dailypulse.sources.domain.repositories.SourcesRepository
import com.angelorobson.dailypulse.sources.presentation.SourcesViewModel
import org.koin.dsl.module


val sourcesModule = module {

    single { SourceEntityMapper() }
    single { SourceMapper() }

    single<SourceApi> { SourcesApiImpl(get()) }
    single<SourcesRemoteDataSource> { SourcesRemoteDataSourceImpl(get()) }
    single<SourcesLocalDataSource> { SourcesLocalDataSourceImpl(get(), get()) }
    single<SourcesRepository> { SourcesRepositoryImpl(get(), get()) }
    single<SourcesUseCase> { SourcesUseCase(get(), get()) }
    single<SourcesViewModel> { SourcesViewModel(get()) }
}