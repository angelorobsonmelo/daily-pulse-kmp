package com.angelorobson.dailypulse.android.modules.article.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesAppBar(
    onAboutButtonClick: () -> Unit, onSourcesButtonClick: () -> Unit
) {
    TopAppBar(title = { Text(text = "Articles") }, actions = {
        IconButton(onClick = onSourcesButtonClick) {
            Icon(
                imageVector = Icons.Outlined.List,
                contentDescription = "Sources Button",
            )
        }
        IconButton(onClick = onAboutButtonClick) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "About Device Button",
            )
        }
    })
}