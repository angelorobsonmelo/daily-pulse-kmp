package com.angelorobson.dailypulse.sources.data.local

import com.angelorobson.dailypulse.db.DailyPulseDatabase
import com.angelorobson.dailypulse.sources.data.network.responses.SourceRawResponse
import com.angelorobson.dailypulse.sources.domain.local.SourcesLocalDataSource


class SourcesLocalDataSourceImpl(
    private val db: DailyPulseDatabase,
    private val sourceEntityMapper: SourceEntityMapper
) : SourcesLocalDataSource {

    override fun getAllSources(): List<SourceRawResponse> =
        db.dailyPulseDatabaseQueries.selectAllSources().executeAsList()
            .map {
                sourceEntityMapper.mapSource(
                    it.id,
                    it.name,
                    it.desc,
                    it.language,
                    it.country
                )
            }

    override fun clearSources() =
        db.dailyPulseDatabaseQueries.removeAllSources()

    override fun createSources(sources: List<SourceRawResponse>) {
        db.dailyPulseDatabaseQueries.transaction {
            sources.forEach { source ->
                insertSource(source)
            }
        }
    }

    private fun insertSource(source: SourceRawResponse) {
        db.dailyPulseDatabaseQueries.insertSource(
            source.id,
            source.name,
            source.desc,
            source.language,
            source.country,
        )
    }
}