package com.angelorobson.dailypulse.articles.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleRawResponse(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val desc: String?,
    @SerialName("publishedAt")
    val date: String,
    @SerialName("urlToImage")
    val imageUrl: String?
)