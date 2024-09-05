package com.angelorobson.dailypulse.sources.data.local.mapper

import com.angelorobson.dailypulse.db.SourceEntity
import com.angelorobson.dailypulse.sources.domain.models.Source

class SourceEntityToDomainMapper {

    fun mapSources(sources: SourceEntity): Source {

          return  Source(
                id = sources.id,
                name = sources.name,
                desc = sources.desc,
                language = sources.language,
                country = sources.country,
                origin = mapOrigin(sources)
            )

    }

    private fun mapOrigin(entity: SourceEntity) = "${entity.country} - ${entity.language}"
}