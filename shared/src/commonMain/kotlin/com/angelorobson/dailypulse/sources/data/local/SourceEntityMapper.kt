package com.angelorobson.dailypulse.sources.data.local

import com.angelorobson.dailypulse.sources.data.network.responses.SourceRawResponse

class SourceEntityMapper {

    fun mapSource(
        id: String,
        name: String,
        desc: String,
        language: String,
        country: String
    ): SourceRawResponse {
        return SourceRawResponse(
            id,
            name,
            desc,
            language,
            country
        )
    }
}