package com.jyoti.androidtutorial.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

interface ViewEvent
interface ViewReduceAction
interface ViewState
interface ViewSideEffect

abstract class BaseViewModel<Event : ViewEvent, ReduceAction : ViewReduceAction, UiState : ViewState, SideEffect : ViewSideEffect>(
    initialState: UiState
) : ViewModel() {

    private val _state: MutableState<UiState> = mutableStateOf(initialState)
    val state: State<UiState> = _state

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> = _event

    private val _action: MutableSharedFlow<ReduceAction> = MutableSharedFlow()
    val action: SharedFlow<ReduceAction> = _action

    private val _effect: Channel<SideEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: Event) {
        viewModelScope.launch {
            executeEvent(event)
        }
    }

    protected fun onAction(action: ReduceAction) {
        viewModelScope.launch {
            _state.value = reduceState(state.value, action)
        }
    }

    protected fun onEffect(builder: () -> SideEffect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    abstract fun executeEvent(event: Event)
    abstract fun reduceState(state: UiState, action: ReduceAction): UiState

}