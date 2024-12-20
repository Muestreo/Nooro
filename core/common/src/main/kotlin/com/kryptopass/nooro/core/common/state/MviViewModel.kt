package com.kryptopass.nooro.core.common.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<T : Any, S : UiState<T>, A : UiAction, E : UiSingleEvent> :
    ViewModel() {

    // NOTE: suitable for managing immutable state within app,
    // typically used with a single collector,
    // emits data continuously (hot stream) and acts as single source of truth
    private val _uiStateFlow: MutableStateFlow<S> by lazy {
        MutableStateFlow(initState())
    }
    val uiStateFlow: StateFlow<S> = _uiStateFlow

    // NOTE: suitable for sharing mutable data among multiple components,
    // supports multiple subscribers, buffering, and limited replay
    // observable stream of events (hot stream)
    private val actionFlow: MutableSharedFlow<A> = MutableSharedFlow()

    // NOTE: suitable for handling one-time events (e.g. navigation, toast, snack-bars)
    private val _singleEventFlow = Channel<E>()
    val singleEventFlow = _singleEventFlow.receiveAsFlow()

    init {
        viewModelScope.launch {
            actionFlow.collect {
                handleAction(it)
            }
        }
    }

    // NOTE: MutableStateFlow requires an initial value, since we don't have concrete value to
    // initialize, we will provide initial value in abstract initState() function
    abstract fun initState(): S

    // NOTE: when new actions are submitted (i.e. user actions, screen load)
    abstract fun handleAction(action: A)

    // NOTE: notify viewmodel of any changes via emit
    fun submitAction(action: A) {
        viewModelScope.launch {
            actionFlow.emit(action)
        }
    }

    // NOTE: update state
    fun submitState(state: S) {
        viewModelScope.launch {
            _uiStateFlow.value = state
        }
    }

    // NOTE: send event
    fun submitSingleEvent(event: E) {
        viewModelScope.launch {
            _singleEventFlow.send(event)
        }
    }
}