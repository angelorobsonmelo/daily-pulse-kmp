package com.angelorobson.dailypulse.articles.presentation

import com.angelorobson.dailypulse.BaseViewModel
import com.angelorobson.dailypulse.articles.domain.usecases.ArticleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val useCase: ArticleUseCase
) : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))

    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    fun getArticles(forceFetch: Boolean = false) {
        scope.launch {
            useCase(forceFetch)
                .onStart {
                    _articlesState.emit(
                        ArticlesState(
                            loading = true,
                            articles = _articlesState.value.articles
                        )
                    )
                }
                .catch {
                    _articlesState.emit(ArticlesState(error = it.message))
                }.collect {
                    _articlesState.emit(ArticlesState(articles = it))
                }
        }
    }


}