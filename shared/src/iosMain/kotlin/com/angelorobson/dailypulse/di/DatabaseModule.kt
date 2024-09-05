package com.angelorobson.dailypulse.di

import com.angelorobson.dailypulse.db.DailyPulseDatabase
import com.angelorobson.dailypulse.db.DatabaseDriverFactory
import org.koin.dsl.module

val dataBaseModule = module {
    single { DatabaseDriverFactory().createDriver() }
    single { DailyPulseDatabase(get()) }
}