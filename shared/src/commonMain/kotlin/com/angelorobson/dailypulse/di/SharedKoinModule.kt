package com.angelorobson.dailypulse.di

import com.angelorobson.dailypulse.articles.di.articlesModule

val sharedKoinModules = listOf(
    networkModule,
    articlesModule
)