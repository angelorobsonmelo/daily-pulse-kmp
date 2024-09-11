package com.angelorobson.dailypulse.sources.data.repositories

import com.angelorobson.dailypulse.db.SourceEntity
import com.angelorobson.dailypulse.sources.data.local.mapper.SourceEntityToDomainMapper
import com.angelorobson.dailypulse.sources.data.network.mapper.SourceResponseToDomainMapper
import com.angelorobson.dailypulse.sources.data.network.responses.SourceRawResponse
import com.angelorobson.dailypulse.sources.domain.local.SourcesLocalDataSource
import com.angelorobson.dailypulse.sources.domain.models.Source
import com.angelorobson.dailypulse.sources.domain.network.SourcesRemoteDataSource
import com.angelorobson.dailypulse.sources.domain.repositories.SourcesRepository
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SourcesRepositoryImplTest : TestsWithMocks() {

    override fun setUpMocks() = injectMocks(mocker)

    @Mock
    lateinit var remoteDataSource: SourcesRemoteDataSource

    @Mock
    lateinit var localDataSource: SourcesLocalDataSource

    @Fake
    lateinit var entity: SourceEntity

    @Fake
    lateinit var sourceRawResponse: SourceRawResponse

    @Fake
    lateinit var source: Source

    private val sourceResponseToDomainMapper = SourceResponseToDomainMapper()
    private val sourceEntityToDomainMapper = SourceEntityToDomainMapper()

    private val repository: SourcesRepository by withMocks {
        SourcesRepositoryImpl(
            dataSource = localDataSource,
            service = remoteDataSource,
            mapper = sourceResponseToDomainMapper,
            sourceEntityMapper = sourceEntityToDomainMapper
        )
    }

    @Test
    fun `getLocalSources should call getAllSources from localDataSource`() = runTest {
        every { localDataSource.getAllSources() } returns listOf(entity)

        val sources = repository.getLocalSources()

        verify { localDataSource.getAllSources() }
        assertFalse(sources.isEmpty())
        assertTrue(sources.isNotEmpty())
    }

    @Test
    fun `fetchRemoteSources should call fetchSources from remoteDataSource`() = runTest {
        everySuspending { remoteDataSource.fetchSources() } returns listOf(sourceRawResponse)

        val sources = repository.fetchRemoteSources()

        verifyWithSuspend { remoteDataSource.fetchSources() }
        assertFalse(sources.isEmpty())
        assertTrue(sources.isNotEmpty())
    }

    @Test
    fun `createSources should call createSources from localDataSource`() = runTest {
        val list = listOf(source)
        every { localDataSource.createSources(list) } returns Unit

        repository.createSources(list)

        verify { localDataSource.createSources(isEqual(list)) }
    }

    @Test
    fun `clearLocalSources should call clearSources from localDataSource`() = runTest {
        every { localDataSource.clearSources() } returns Unit

        repository.clearLocalSources()

        verify { localDataSource.clearSources() }
    }


}