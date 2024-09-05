package com.angelorobson.dailypulse.sources.domain.local

import com.angelorobson.dailypulse.db.SourceEntity
import com.angelorobson.dailypulse.sources.domain.models.Source

interface SourcesLocalDataSource {

    fun getAllSources(): List<SourceEntity>

    fun clearSources()

    fun createSources(sources: List<Source>)
}