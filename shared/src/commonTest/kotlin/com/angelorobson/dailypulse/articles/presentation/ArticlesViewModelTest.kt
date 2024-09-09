package com.angelorobson.dailypulse.articles.presentation

import app.cash.turbine.test
import com.angelorobson.dailypulse.articles.domain.models.Article
import com.angelorobson.dailypulse.articles.domain.usecases.ArticleUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue


@OptIn(ExperimentalCoroutinesApi::class)
class ArticlesViewModelTest : TestsWithMocks() {

    @Mock
    lateinit var articleUseCase: ArticleUseCase

    override fun setUpMocks() = injectMocks(mocker)

    private val viewModel: ArticlesViewModel by withMocks {
        ArticlesViewModel(useCase = articleUseCase)
    }


    @Test
    fun `getArticles should call useCase successfully`() = runTest {
        viewModel.scope = CoroutineScope(UnconfinedTestDispatcher())

        everySuspending { articleUseCase(isAny()) } returns flowOf(
            listOf(
                Article(
                    title = "title",
                    desc = "description",
                    date = "url",
                    imageUrl = "imageUrl",
                )
            )
        )

        viewModel.getArticles()

        viewModel.articlesState.test {
            val state = awaitItem()

            assertFalse(state.loading)
            assertNull(state.error)
            assertTrue(state.articles.isNotEmpty())
        }
    }

    @Test
    fun `getArticles should call useCase with error`() = runTest {
        val errorMsg = "Error Ex"
        viewModel.scope = CoroutineScope(UnconfinedTestDispatcher())

        everySuspending { articleUseCase(isAny()) } returns callbackFlow {
            throw Exception(errorMsg)
        }

        viewModel.getArticles()

        viewModel.articlesState.test {
            val state = awaitItem()

            assertFalse(state.articles.isNotEmpty())
            assertFalse(state.loading)
            assertNotNull(state.error)
            assertEquals(errorMsg, state.error)
        }
    }


}


