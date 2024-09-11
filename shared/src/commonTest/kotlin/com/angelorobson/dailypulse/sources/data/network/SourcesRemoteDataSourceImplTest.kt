package com.angelorobson.dailypulse.sources.data.network

import com.angelorobson.dailypulse.sources.data.network.responses.SourceRawResponse
import com.angelorobson.dailypulse.sources.domain.network.SourcesRemoteDataSource
import com.angelorobson.dailypulse.sources.domain.network.api.SourceApi
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Fake
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SourcesRemoteDataSourceImplTest : TestsWithMocks() {

    override fun setUpMocks() = injectMocks(mocker)

    @Mock
    lateinit var sourceApi: SourceApi

    @Fake
    lateinit var list: SourceRawResponse

    private val remoteDataSource: SourcesRemoteDataSource by withMocks {
        SourcesRemoteDataSourceImpl(sourceApi)
    }

    @Test
    fun `fetchSources should call fetchSources from sourceApi`() = runTest {
        everySuspending { sourceApi.fetchSources() } returns listOf(list)

        val sources = remoteDataSource.fetchSources()

        verifyWithSuspend { sourceApi.fetchSources() }
        assertFalse(sources.isEmpty())
        assertTrue(sources.isNotEmpty())
    }
}