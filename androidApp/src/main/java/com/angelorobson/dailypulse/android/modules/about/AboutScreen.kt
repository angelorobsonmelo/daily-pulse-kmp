package com.angelorobson.dailypulse.android.modules.about

import androidx.compose.runtime.Composable
import com.angelorobson.dailypulse.android.modules.about.components.AboutContent

@Composable
fun AboutScreen(
    onUpButtonClick: () -> Unit
) {
    AboutContent(onUpButtonClick)
}
