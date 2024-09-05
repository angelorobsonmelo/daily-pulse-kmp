package com.angelorobson.dailypulse.sources.di

import com.angelorobson.dailypulse.sources.data.local.SourcesDataSource
import com.angelorobson.dailypulse.sources.data.network.SourcesService
import com.angelorobson.dailypulse.sources.data.repositories.SourcesRepository
import com.angelorobson.dailypulse.sources.domain.SourcesUseCase
import com.angelorobson.dailypulse.sources.presentation.SourcesViewModel
import org.koin.dsl.module


val sourcesModule = module {

    single<SourcesService> { SourcesService(get()) }
    single<SourcesUseCase> { SourcesUseCase(get()) }
    single<SourcesDataSource> { SourcesDataSource(get()) }
    single<SourcesRepository> { SourcesRepository(get(), get()) }
    single<SourcesViewModel> { SourcesViewModel(get()) }
}