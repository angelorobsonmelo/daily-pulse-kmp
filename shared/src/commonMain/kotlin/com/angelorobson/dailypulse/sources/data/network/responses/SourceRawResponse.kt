package com.angelorobson.dailypulse.sources.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceRawResponse(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val desc: String,
    @SerialName("language")
    val language: String,
    @SerialName("country")
    val country: String,
)