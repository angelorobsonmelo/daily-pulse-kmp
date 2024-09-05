package com.angelorobson.dailypulse.android.di

import com.angelorobson.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { ArticlesViewModel(get()) }
}