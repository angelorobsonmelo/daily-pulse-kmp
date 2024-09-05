package com.angelorobson.dailypulse.di

import com.angelorobson.dailypulse.articles.di.articlesModule
import com.angelorobson.dailypulse.sources.di.sourcesModule

val sharedKoinModules = listOf(
    networkModule,
    articlesModule,
    sourcesModule
)