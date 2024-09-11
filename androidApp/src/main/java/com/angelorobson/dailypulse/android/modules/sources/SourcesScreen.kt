package com.angelorobson.dailypulse.android.modules.sources


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.angelorobson.dailypulse.android.modules.sources.components.SourceContent
import com.angelorobson.dailypulse.sources.presentation.SourcesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SourcesScreen(
    viewModel: SourcesViewModel = getViewModel(),
    onUpButtonClick: () -> Unit
) {
    val articlesState by viewModel.sourcesState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getSources()
    }

    SourceContent(articlesState, onUpButtonClick)
}






