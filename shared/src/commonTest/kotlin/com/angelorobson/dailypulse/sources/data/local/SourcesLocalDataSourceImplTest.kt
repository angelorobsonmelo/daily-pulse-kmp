package com.angelorobson.dailypulse.sources.data.local

import com.angelorobson.dailypulse.builders.SourceBuilder
import com.angelorobson.dailypulse.db.DailyPulseDatabase
import com.angelorobson.dailypulse.sources.domain.local.SourcesLocalDataSource
import com.angelorobson.dailypulse.testDbConnection
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SourcesLocalDataSourceImplTest {

    private lateinit var dailyPulseDatabase: DailyPulseDatabase
    private lateinit var sourcesLocalDataSource: SourcesLocalDataSource

    private val source = SourceBuilder().build()

    @BeforeTest
    fun setUp() {
        val driver = testDbConnection()
        dailyPulseDatabase = DailyPulseDatabase(driver)
        sourcesLocalDataSource = SourcesLocalDataSourceImpl(dailyPulseDatabase)

        sourcesLocalDataSource.createSources(listOf(source))
    }

    @Test
    fun shouldReturnPlatformInstanceNotNull() {
        assertNotNull(dailyPulseDatabase)
    }

    @Test
    fun `getAllSources should return list of entities`() {
        val sources = sourcesLocalDataSource.getAllSources()
        assertNotNull(sources)
    }

    @Test
    fun `clearSources should return return an empty list`() {
        sourcesLocalDataSource.clearSources()
        val sources = sourcesLocalDataSource.getAllSources()
        assertTrue(sources.isEmpty())
    }
}