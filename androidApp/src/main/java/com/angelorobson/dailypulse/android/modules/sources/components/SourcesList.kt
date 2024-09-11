package com.angelorobson.dailypulse.android.modules.sources.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.angelorobson.dailypulse.sources.presentation.SourcesState

@Composable
fun SourcesList(state: SourcesState) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.sources) { source ->
            SourceItem(source = source)
        }
    }

}