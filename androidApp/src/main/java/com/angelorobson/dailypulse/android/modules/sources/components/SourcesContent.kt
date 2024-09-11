package com.angelorobson.dailypulse.android.modules.sources.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.angelorobson.dailypulse.android.commom.ErrorMessage
import com.angelorobson.dailypulse.sources.presentation.SourcesState

@Composable
fun SourceContent(articlesState: SourcesState, onUpButtonClick: () -> Unit) {
    Column {
        SourceAppBar(onUpButtonClick)

        if (articlesState.error != null)
            ErrorMessage(articlesState.error.toString())

        SourcesList(articlesState)
    }
}