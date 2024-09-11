package com.angelorobson.dailypulse.sources.domain.usescases.impl

import com.angelorobson.dailypulse.sources.domain.models.Source
import com.angelorobson.dailypulse.sources.domain.repositories.SourcesRepository
import com.angelorobson.dailypulse.sources.domain.usecases.SourcesUseCase
import com.angelorobson.dailypulse.sources.domain.usecases.impl.SourcesUseCaseImpl
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SourcesUseCaseImplTest : TestsWithMocks() {

    @Mock
    lateinit var repository: SourcesRepository

    override fun setUpMocks() = injectMocks(mocker)

    @Fake
    lateinit var source: Source

    private val useCase: SourcesUseCase by withMocks {
        SourcesUseCaseImpl(repository)
    }

    @Test
    fun `should invoke and return list of sources from local database`() = runTest {
        everySuspending { repository.getLocalSources() } returns listOf(source)

        useCase()
            .collect {
                assertFalse(it.isEmpty())
                assertTrue(it.isNotEmpty())
            }

        verifyWithSuspend { repository.getLocalSources() }
    }

    @Test
    fun `should invoke and return list of sources from remote database`() = runTest {
        val list = listOf(source)

        everySuspending { repository.getLocalSources() } returns emptyList()
        everySuspending { repository.clearLocalSources() } returns Unit
        everySuspending { repository.fetchRemoteSources() } returns list
        everySuspending { repository.createSources(list) } returns Unit

        useCase()
            .collect {
                assertFalse(it.isEmpty())
                assertTrue(it.isNotEmpty())
            }

        verifyWithSuspend {
            repository.getLocalSources()
            repository.clearLocalSources()
            repository.fetchRemoteSources()
            repository.createSources(isEqual(list))
        }
    }


}