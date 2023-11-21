package com.dicoding.sayurtaniku.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.sayurtaniku.data.SayurRepository
import com.dicoding.sayurtaniku.model.OrderSayur
import com.dicoding.sayurtaniku.model.Sayur
import com.dicoding.sayurtaniku.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class DetailSayurViewModel(
    private val repository: SayurRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderSayur>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderSayur>>
        get() = _uiState

    fun getSayurById(sayurId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderSayurById(sayurId))
        }
    }

    fun addToCart(sayur: Sayur, count: Int) {
        viewModelScope.launch {
            repository.updateOrderSayur(sayur.id, count)

        }
    }
}