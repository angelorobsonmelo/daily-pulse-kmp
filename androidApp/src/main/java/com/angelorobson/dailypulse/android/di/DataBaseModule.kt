package com.angelorobson.dailypulse.android.di

import com.angelorobson.dailypulse.db.DailyPulseDatabase
import com.angelorobson.dailypulse.db.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { DatabaseDriverFactory(androidContext()).createDriver() }
    single { DailyPulseDatabase(get()) }
}