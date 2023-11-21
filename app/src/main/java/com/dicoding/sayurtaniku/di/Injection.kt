package com.dicoding.sayurtaniku.di

import com.dicoding.sayurtaniku.data.SayurRepository

object Injection {
    fun provideRepository(): SayurRepository {
        return SayurRepository.getInstance()
    }
}