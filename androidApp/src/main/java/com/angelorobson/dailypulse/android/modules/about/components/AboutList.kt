package com.angelorobson.dailypulse.android.modules.about.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.angelorobson.dailypulse.Platform

@Composable
fun AboutList() {
    val items = makeItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(items) { (title, description) ->
            AboutItem(title, description)
        }
    }
}

private fun makeItems(): List<Pair<String, String>> {
    val platform = Platform()
    return listOf(
        "Operating System" to "${platform.osName} ${platform.osVersion}",
        "Device" to platform.deviceModel,
        "Density" to platform.density.toString()
    )
}