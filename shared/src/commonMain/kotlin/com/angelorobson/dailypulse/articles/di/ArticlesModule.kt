package com.angelorobson.dailypulse.articles.di

import com.angelorobson.dailypulse.articles.data.network.ArticleRemoteDataSourceImpl
import com.angelorobson.dailypulse.articles.domain.usecases.impl.ArticleUseCaseImpl
import com.angelorobson.dailypulse.articles.data.local.ArticlesLocalDataSourceImpl
import com.angelorobson.dailypulse.articles.data.local.mappers.ArticleEntityToDomainMapper
import com.angelorobson.dailypulse.articles.data.mappers.ArticlesEntityMapper
import com.angelorobson.dailypulse.articles.data.network.api.ArticlesApiImpl
import com.angelorobson.dailypulse.articles.data.network.mappers.ArticleResponseToDomainMapper
import com.angelorobson.dailypulse.articles.data.repositories.ArticlesRepositoryImpl
import com.angelorobson.dailypulse.articles.domain.local.ArticlesLocalDataSource
import com.angelorobson.dailypulse.articles.domain.network.ArticleRemoteDataSource
import com.angelorobson.dailypulse.articles.domain.network.api.ArticlesApi
import com.angelorobson.dailypulse.articles.domain.repositories.ArticlesRepository
import com.angelorobson.dailypulse.articles.domain.usecases.ArticleUseCase
import com.angelorobson.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {

    single { ArticleEntityToDomainMapper() }
    single { ArticleResponseToDomainMapper() }
    single { ArticlesEntityMapper() }

    single<ArticlesApi> { ArticlesApiImpl(get()) }
    single<ArticleRemoteDataSource> { ArticleRemoteDataSourceImpl(get()) }
    single<ArticlesLocalDataSource> { ArticlesLocalDataSourceImpl(get()) }
    single<ArticlesRepository> { ArticlesRepositoryImpl(get(), get(), get(), get()) }
    single<ArticleUseCase> { ArticleUseCaseImpl(get()) }
    single { ArticlesViewModel(get()) }

}