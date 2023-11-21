package com.dicoding.sayurtaniku.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object DetailSayur : Screen("home/{sayurId}") {
        fun createRoute(sayurId: Long) = "home/$sayurId"
    }
}
