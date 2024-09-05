package com.angelorobson.dailypulse.articles.di

import com.angelorobson.dailypulse.articles.data.network.ArticleService
import com.angelorobson.dailypulse.articles.domain.ArticleUseCase
import com.angelorobson.dailypulse.articles.data.local.ArticlesDataSource
import com.angelorobson.dailypulse.articles.data.repositories.ArticlesRepository
import com.angelorobson.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {

    single { ArticleService(get()) }
    single { ArticleUseCase(get()) }
    single { ArticlesViewModel(get()) }
    single { ArticlesDataSource(get()) }
    single { ArticlesRepository(get(), get()) }

}