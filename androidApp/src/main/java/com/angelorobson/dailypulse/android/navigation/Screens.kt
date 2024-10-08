package com.angelorobson.dailypulse.android.navigation

import kotlinx.serialization.Serializable



@Serializable
sealed class Screens {

    @Serializable
    data object Articles : Screens()

    @Serializable
    data object Sources : Screens()

    @Serializable
    data object AboutDevice : Screens()
}