package com.angelorobson.dailypulse

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.inMemoryDriver
import com.angelorobson.dailypulse.db.DailyPulseDatabase

internal actual fun testDbConnection(): SqlDriver {
    return inMemoryDriver(DailyPulseDatabase.Schema)
}