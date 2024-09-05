package com.angelorobson.dailypulse.articles.data.mappers

import com.angelorobson.dailypulse.articles.data.network.responses.ArticleRawResponse

class ArticlesEntityMapper {

    fun mapToArticleRaw(
        title: String,
        desc: String?,
        date: String,
        url: String?
    ): ArticleRawResponse =
        ArticleRawResponse(
            title,
            desc,
            date,
            url
        )
}