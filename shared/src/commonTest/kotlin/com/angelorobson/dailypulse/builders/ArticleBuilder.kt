package com.angelorobson.dailypulse.builders

import com.angelorobson.dailypulse.articles.domain.models.Article

class ArticleBuilder {
    private var title: String = "title"
    private var desc: String = "desc"
    private var date: String = "2024-09-06T00:00:00Z"
    private var imageUrl: String = "imageUrl"

    fun title(title: String) = apply { this.title = title }
    fun desc(desc: String) = apply { this.desc = desc }
    fun date(date: String) = apply { this.date = date }
    fun imageUrl(imageUrl: String) = apply { this.imageUrl = imageUrl }

    fun build() = Article(title, desc, date, imageUrl)

}