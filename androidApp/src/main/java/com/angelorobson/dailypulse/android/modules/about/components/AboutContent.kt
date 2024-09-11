package com.angelorobson.dailypulse.android.modules.about.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun AboutContent(
    onUpButtonClick: () -> Unit
) {
    Column {
        AboutToolBar(onUpButtonClick)
        AboutList()
    }
}