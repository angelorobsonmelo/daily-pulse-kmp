package com.angelorobson.dailypulse.sources.data.network.mapper

import com.angelorobson.dailypulse.sources.data.network.responses.SourceRawResponse
import com.angelorobson.dailypulse.sources.domain.models.Source

class SourceResponseToDomainMapper {

    fun responseToModel(sourceRawResponse: SourceRawResponse) = sourceRawResponse.run {
        Source(
            id = id,
            name = name,
            desc = desc,
            language = language,
            country = country,
            origin = mapOrigin(this)
        )
    }

    private fun mapOrigin(raw: SourceRawResponse) = "${raw.country} - ${raw.language}"
}