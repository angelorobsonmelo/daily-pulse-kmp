package com.angelorobson.dailypulse.sources.presentation

import com.angelorobson.dailypulse.BaseViewModel
import com.angelorobson.dailypulse.sources.domain.usecases.SourcesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SourcesViewModel(private val useCase: SourcesUseCase) : BaseViewModel() {

    private val _sourcesState =
        MutableStateFlow(SourcesState(loading = true))
    val sourcesState: StateFlow<SourcesState> get() = _sourcesState

    fun getSources() {
        scope.launch {
            useCase()
                .onStart {
                    _sourcesState.emit(SourcesState(_sourcesState.value.sources, true))
                }.catch {
                    _sourcesState.emit(SourcesState(error = it.message))
                }.collect { sources ->
                    _sourcesState.emit(SourcesState(sources))
                }
        }
    }
}