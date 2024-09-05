package com.angelorobson.dailypulse.sources.data.local

import com.angelorobson.dailypulse.db.DailyPulseDatabase
import com.angelorobson.dailypulse.db.SourceEntity
import com.angelorobson.dailypulse.sources.data.network.responses.SourceRawResponse
import com.angelorobson.dailypulse.sources.domain.local.SourcesLocalDataSource
import com.angelorobson.dailypulse.sources.domain.models.Source


class SourcesLocalDataSourceImpl(
    private val db: DailyPulseDatabase
) : SourcesLocalDataSource {

    override fun getAllSources(): List<SourceEntity> =
        db.dailyPulseDatabaseQueries.selectAllSources().executeAsList()

    override fun clearSources() =
        db.dailyPulseDatabaseQueries.removeAllSources()

    override fun createSources(sources: List<Source>) {
        db.dailyPulseDatabaseQueries.transaction {
            sources.forEach { source ->
                insertSource(source)
            }
        }
    }

    private fun insertSource(source: Source) {
        db.dailyPulseDatabaseQueries.insertSource(
            source.id,
            source.name,
            source.desc,
            source.language,
            source.country,
            source.origin
        )
    }
}