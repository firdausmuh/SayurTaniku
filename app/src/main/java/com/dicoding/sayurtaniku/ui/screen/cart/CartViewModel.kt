package com.dicoding.sayurtaniku.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.sayurtaniku.data.SayurRepository
import com.dicoding.sayurtaniku.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: SayurRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderSayurs() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderSayurs()
                .collect { orderSayur ->
                    val totalRequiredPoint =
                        orderSayur.sumOf { it.sayur.requiredHarga * it.count }
                    _uiState.value = UiState.Success(CartState(orderSayur, totalRequiredPoint))
                }
        }
    }

    fun updateOrderSayur(sayurId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderSayur(sayurId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderSayurs()
                    }
                }
        }
    }
}