package com.angelorobson.dailypulse.sources.presentation

import com.angelorobson.dailypulse.sources.domain.models.Source


data class SourcesState (
    val sources: List<Source> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)