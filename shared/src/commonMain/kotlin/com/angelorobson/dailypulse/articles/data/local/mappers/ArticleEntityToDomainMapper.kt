package com.angelorobson.dailypulse.articles.data.local.mappers

import com.angelorobson.dailypulse.articles.domain.models.Article
import com.angelorobson.dailypulse.db.ArticleEntity

class ArticleEntityToDomainMapper {

    fun mapArticles(entity: ArticleEntity): Article =
        Article(
            title = entity.title,
            desc = entity.desc,
            date = entity.date,
            imageUrl = entity.imageUrl
        )
}