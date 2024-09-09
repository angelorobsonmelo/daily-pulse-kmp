package com.angelorobson.dailypulse

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {

    var scope: CoroutineScope
}