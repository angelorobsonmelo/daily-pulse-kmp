package com.angelorobson.dailypulse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

actual open class BaseViewModel : ViewModel() {

    actual var scope = viewModelScope
}