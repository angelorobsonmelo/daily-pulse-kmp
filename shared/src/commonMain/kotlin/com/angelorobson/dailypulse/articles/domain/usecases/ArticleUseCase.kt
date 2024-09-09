package com.angelorobson.dailypulse.articles.domain.usecases

import com.angelorobson.dailypulse.articles.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface ArticleUseCase
{

    suspend operator fun invoke(forceFetch: Boolean): Flow<List<Article>>

}