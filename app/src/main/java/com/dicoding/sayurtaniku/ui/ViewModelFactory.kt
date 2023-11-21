package com.dicoding.sayurtaniku.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.sayurtaniku.data.SayurRepository
import com.dicoding.sayurtaniku.ui.screen.cart.CartViewModel
import com.dicoding.sayurtaniku.ui.screen.detail.DetailSayurViewModel
import com.dicoding.sayurtaniku.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: SayurRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailSayurViewModel::class.java)) {
            return DetailSayurViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
