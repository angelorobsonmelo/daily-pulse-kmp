package com.angelorobson.dailypulse.articles.di

import com.angelorobson.dailypulse.articles.data.network.ArticleRemoteDataSourceImpl
import com.angelorobson.dailypulse.articles.domain.ArticleUseCase
import com.angelorobson.dailypulse.articles.data.local.ArticlesLocalDataSourceImpl
import com.angelorobson.dailypulse.articles.data.mappers.ArticlesEntityMapper
import com.angelorobson.dailypulse.articles.data.network.api.ArticlesApiImpl
import com.angelorobson.dailypulse.articles.data.repositories.ArticlesRepositoryImpl
import com.angelorobson.dailypulse.articles.domain.local.ArticlesLocalDataSource
import com.angelorobson.dailypulse.articles.domain.mappers.ArticlesMapper
import com.angelorobson.dailypulse.articles.domain.network.ArticleRemoteDataSource
import com.angelorobson.dailypulse.articles.domain.network.api.ArticlesApi
import com.angelorobson.dailypulse.articles.domain.repositories.ArticlesRepository
import com.angelorobson.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {

    single { ArticlesMapper() }
    single { ArticlesEntityMapper() }

    single<ArticlesApi> { ArticlesApiImpl(get())  }
    single<ArticleRemoteDataSource> { ArticleRemoteDataSourceImpl(get()) }
    single<ArticlesLocalDataSource> { ArticlesLocalDataSourceImpl(get(), get()) }
    single<ArticlesRepository> { ArticlesRepositoryImpl(get(), get()) }
    single { ArticleUseCase(get(), get()) }
    single { ArticlesViewModel(get()) }

}