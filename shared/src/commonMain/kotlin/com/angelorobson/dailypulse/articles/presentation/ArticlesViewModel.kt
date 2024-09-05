package com.angelorobson.dailypulse.articles.presentation

import com.angelorobson.dailypulse.BaseViewModel
import com.angelorobson.dailypulse.articles.domain.ArticleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val useCase: ArticleUseCase
) : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))
    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    init {
        getArticles()
    }

    fun getArticles(forceFetch: Boolean = false) {
        scope.launch {
            _articlesState.emit(ArticlesState(loading = true, articles = _articlesState.value.articles))

            val fetchedArticles = useCase.getArticles(forceFetch)

            _articlesState.emit(ArticlesState(articles = fetchedArticles))
        }
    }


}