package com.angelorobson.dailypulse.articles

import com.angelorobson.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))
    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            delay(1500)

            _articlesState.emit(ArticlesState(error = "Something went wrong"))

            delay(1500)

            val fetchedArticles = fetchArticles()

            _articlesState.emit(ArticlesState(articles = fetchedArticles))
        }
    }

    private suspend fun fetchArticles(): List<Article> = mockArticle

    private val mockArticle = listOf(
        Article(
            title = "Title",
            description = "Description",
            date = "2023-11-09",
            imageUrl = "https://yt3.googleusercontent.com/iUQqZPVMfTDE0ByCGHMQHRnUpJTOkOuyyD8P0wu3sDCx7EPenAKmNueijl3Y13zogxT902NVCg=s900-c-k-c0x00ffffff-no-rj",
        ),
        Article(
            title = "Title1",
            description = "Description2",
            date = "2023-11-09",
            imageUrl = "https://yt3.googleusercontent.com/iUQqZPVMfTDE0ByCGHMQHRnUpJTOkOuyyD8P0wu3sDCx7EPenAKmNueijl3Y13zogxT902NVCg=s900-c-k-c0x00ffffff-no-rj",
        ),
        Article(
            title = "Title3",
            description = "Description4",
            date = "2023-11-09",
            imageUrl = "https://yt3.googleusercontent.com/iUQqZPVMfTDE0ByCGHMQHRnUpJTOkOuyyD8P0wu3sDCx7EPenAKmNueijl3Y13zogxT902NVCg=s900-c-k-c0x00ffffff-no-rj",
        )
    )


}