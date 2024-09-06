package com.angelorobson.dailypulse

import kotlin.test.Test
import kotlin.test.assertEquals

class PlatformTest {

    private val platform = Platform()

    @Test
    fun shouldReturnOsName() {
        assertEquals(
            "iOS",
            platform.osName
        )
    }
}