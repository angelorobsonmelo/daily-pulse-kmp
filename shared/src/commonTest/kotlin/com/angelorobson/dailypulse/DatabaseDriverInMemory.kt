package com.angelorobson.dailypulse

import app.cash.sqldelight.db.SqlDriver

internal expect fun testDbConnection(): SqlDriver
