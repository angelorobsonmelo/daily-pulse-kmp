package com.angelorobson.dailypulse.sources.data.network.api

import com.angelorobson.dailypulse.TestUtils.getClient
import com.angelorobson.dailypulse.TestUtils.sourcesJsonResponseBody
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SourcesApiImplTest {

    @Test
    fun `fetchSources should return a list of Sources`() = runTest {
        val mockEngine = MockEngine {
            respond(
                content = sourcesJsonResponseBody(),
                headers = headersOf(
                    HttpHeaders.ContentType,
                    ContentType.Application.Json.toString()
                )
            )
        }

        val client = getClient(engine = mockEngine)

        val api = SourcesApiImpl(client)

        val sources = api.fetchSources()

        assertFalse(sources.isEmpty())
        assertTrue(sources.isNotEmpty())
    }
}