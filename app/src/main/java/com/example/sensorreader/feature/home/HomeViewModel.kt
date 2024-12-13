package com.example.sensorreader.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val state: StateFlow<HomeUiState>
        get() = _state.asStateFlow()

    fun onFetchButtonClick(isIdleState: Boolean) = viewModelScope.launch {
        _state.value = if (isIdleState) {
            HomeUiState.Content("", "")
        } else {
            HomeUiState.Idle
        }
    }
}