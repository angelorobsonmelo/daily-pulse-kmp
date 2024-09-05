package com.angelorobson.dailypulse.sources.domain.models


data class Source(
    val id: String,
    val name: String,
    val desc: String,
    val origin: String,
    val language: String,
    val country: String,
)