package com.pepinho.pmdm.pokemonnav.navigation


sealed class NavItem(val route: String, val title: String, val icon: Int) {
    // Rutas de navegación
    object Home : NavItem("home", "Inicio", android.R.drawable.ic_menu_view)
    object Search : NavItem("search", "Búsqueda", android.R.drawable.ic_menu_search)
    object Settings : NavItem("settings", "Configuración", android.R.drawable.ic_menu_preferences)
}