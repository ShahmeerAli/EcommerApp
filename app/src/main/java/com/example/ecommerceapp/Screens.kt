package com.example.ecommerceapp

sealed class Screens(val screen: String) {
    data object HomeScreen: Screens("home")
    data object SearchScreen: Screens("search")
    data object CartScreen: Screens("cart")
    data object SettingsScreen: Screens("settings")
    data object MenClothing: Screens("men")
}