package com.angelorobson.dailypulse.android.modules.sources.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.angelorobson.dailypulse.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SourceAppBar(
    onUpButtonClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.sources)) },
        navigationIcon = {
            IconButton(onClick = onUpButtonClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Up Button",
                )
            }
        }
    )
}