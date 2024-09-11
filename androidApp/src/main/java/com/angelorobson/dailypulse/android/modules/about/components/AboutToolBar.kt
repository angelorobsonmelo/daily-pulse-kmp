package com.angelorobson.dailypulse.android.modules.about.components

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
fun AboutToolBar(onUpButtonClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.about_device))
        },
        navigationIcon = {
            IconButton(onClick = onUpButtonClick) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Up Button"
                )
            }
        }
    )
}