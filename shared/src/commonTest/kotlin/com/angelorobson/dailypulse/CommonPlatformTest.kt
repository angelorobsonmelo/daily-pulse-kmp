package com.angelorobson.dailypulse

import kotlin.test.Test
import kotlin.test.assertNotNull

class CommonGreetingTest {

    private val platform = Platform()

    @Test
    fun shouldReturnPlatformInstanceNotNull() {
        assertNotNull(platform)
    }

}