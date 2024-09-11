package com.angelorobson.dailypulse.sources.presentation

import app.cash.turbine.test
import com.angelorobson.dailypulse.sources.domain.models.Source
import com.angelorobson.dailypulse.sources.domain.usecases.SourcesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue


@OptIn(ExperimentalCoroutinesApi::class)
class SourcesViewModelTest : TestsWithMocks() {

    @Mock
    lateinit var sourcesUseCase: SourcesUseCase

    @Fake
    lateinit var source: Source

    override fun setUpMocks() = injectMocks(mocker)

    private val viewModel: SourcesViewModel by withMocks {
        SourcesViewModel(useCase = sourcesUseCase)
    }

    @AfterTest
    fun tearDown() {
        viewModel.scope.cancel()
    }

    @Test
    fun `getSources should call useCase successfully`() = runTest {
        viewModel.scope = CoroutineScope(UnconfinedTestDispatcher())

        everySuspending { sourcesUseCase() } returns flowOf(listOf(source))

        viewModel.getSources()

        viewModel.sourcesState.test {
            val state = awaitItem()

            assertFalse(state.loading)
            assertNull(state.error)
            assertTrue(state.sources.isNotEmpty())
        }
    }

    @Test
    fun `getSources should call useCase and return error`() = runTest {
        val errorMsg = "Error Ex"

        viewModel.scope = CoroutineScope(UnconfinedTestDispatcher())

        everySuspending { sourcesUseCase() }  returns callbackFlow {
            throw Exception(errorMsg)
        }

        viewModel.getSources()

        viewModel.sourcesState.test {
            val state = awaitItem()

            assertFalse(state.sources.isNotEmpty())
            assertFalse(state.loading)
            assertNotNull(state.error)
            assertEquals(errorMsg, state.error)
        }
    }

}