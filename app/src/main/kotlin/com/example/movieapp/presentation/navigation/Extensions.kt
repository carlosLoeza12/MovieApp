package com.example.movieapp.presentation.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

fun NavBackStack<NavKey>.navigateTo(screen: NavKey) {

    this.add(screen)
}

fun NavBackStack<NavKey>.navigateAndReplace(screen: NavKey)  {

    this.clear()
    this.add(screen)
}

fun NavBackStack<NavKey>.back() {

    if (this.isEmpty()) return
    this.removeLastOrNull()
}

fun NavBackStack<NavKey>.backTo(screen: NavKey) {

    if (this.isEmpty()) return
    if (screen !in this) return

    while (this.isNotEmpty() && this.last() != screen) {
        this.removeLastOrNull()
    }
}
