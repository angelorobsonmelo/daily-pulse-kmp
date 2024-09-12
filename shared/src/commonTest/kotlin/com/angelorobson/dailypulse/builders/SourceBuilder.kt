package com.angelorobson.dailypulse.builders

import com.angelorobson.dailypulse.sources.domain.models.Source

class SourceBuilder {
    private var id: String = "id"
    private var name: String = "Name"
    private var desc: String = "desc"
    private var origin: String = "origin"
    private var language: String = "language"
    private var country: String = "country"

    fun id(id: String) = apply { this.id = id }
    fun name(name: String) = apply { this.name = name }
    fun desc(desc: String) = apply { this.desc = desc }
    fun origin(origin: String) = apply { this.origin = origin }
    fun language(language: String) = apply { this.language = language }
    fun country(country: String) = apply { this.country = country }

    fun build() = Source(
        id = id,
        name = name,
        desc = desc,
        origin = origin,
        language = language,
        country = country
    )
}