package com.dicoding.sayurtaniku.ui.screen.cart

import com.dicoding.sayurtaniku.model.OrderSayur

data class CartState(
    val orderSayur: List<OrderSayur>,
    val totalRequiredHarga: Int
)