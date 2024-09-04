package com.angelorobson.dailypulse.articles.di

import com.angelorobson.dailypulse.articles.ArticleService
import com.angelorobson.dailypulse.articles.ArticleUseCase
import com.angelorobson.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {

    single { ArticleService(get()) }
    single { ArticleUseCase(get()) }
    single { ArticlesViewModel(get()) }

}