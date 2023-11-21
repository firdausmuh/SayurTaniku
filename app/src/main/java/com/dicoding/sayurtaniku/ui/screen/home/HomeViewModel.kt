package com.dicoding.sayurtaniku.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.sayurtaniku.data.SayurRepository
import com.dicoding.sayurtaniku.model.OrderSayur
import com.dicoding.sayurtaniku.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: SayurRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderSayur>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderSayur>>>
        get() = _uiState

    fun getAllSayur() {
        viewModelScope.launch {
            repository.getAllSayurs()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderSayur ->
                    _uiState.value = UiState.Success(orderSayur)
                }
        }
    }
}