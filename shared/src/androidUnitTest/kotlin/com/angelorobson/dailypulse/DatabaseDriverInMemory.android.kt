package com.angelorobson.dailypulse

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.angelorobson.dailypulse.db.DailyPulseDatabase

internal actual fun testDbConnection(): SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    .also { DailyPulseDatabase.Schema.create(it) }