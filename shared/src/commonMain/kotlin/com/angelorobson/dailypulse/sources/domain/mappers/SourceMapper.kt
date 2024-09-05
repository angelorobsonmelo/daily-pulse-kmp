package com.angelorobson.dailypulse.sources.domain.mappers

import com.angelorobson.dailypulse.sources.data.network.responses.SourceRawResponse
import com.angelorobson.dailypulse.sources.domain.models.Source

class SourceMapper {

    fun mapSources(sourcesRaw: List<SourceRawResponse>): List<Source> =
        sourcesRaw.map { raw ->
            Source(
                raw.id,
                raw.name,
                raw.desc,
                mapOrigin(raw),
            )
        }

    private fun mapOrigin(raw: SourceRawResponse) = "${raw.country} - ${raw.language}"
}