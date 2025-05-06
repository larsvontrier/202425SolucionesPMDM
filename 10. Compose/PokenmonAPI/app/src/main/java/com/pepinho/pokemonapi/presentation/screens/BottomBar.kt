package com.pepinho.pokemonapi.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.pepinho.pokemonapi.presentation.navigation.BottomNavItem
import com.pepinho.pokemonapi.presentation.navigation.Screen

@Composable
fun BottomBar(
    onClick: (String) -> Unit,
    selectedScreen: String,
    navController: NavHostController,
    viewModel: PokemonViewModel
) {
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(1)
    }
    val items = listOf(
        BottomNavItem(
            name = "Inicio",
            route = Screen.Inicio.rout,
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            name = "Pokemon",
            route = Screen.Pokemon.rout,
            icon = Icons.AutoMirrored.Filled.List
        ),
        BottomNavItem(
            name = "Búsqueda",
            route = Screen.Search.rout,
            icon = Icons.Default.Search
        ),
        BottomNavItem(
            name = "Configuración",
            route = Screen.Setting.rout,
            icon = Icons.Default.Settings
        )
    )

    BottomAppBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.value = index
                    onClick(item.route)
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name
                    )
                },
                label = {
                    Text(text = item.name,
                        color = if (index == selectedNavigationIndex.intValue) Color.Black
                        else Color.Gray)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

//@Composable
//fun BottomNavigationBar(items: List<BottomNavItem>, onItemClick: (String) -> Unit, selectedScreen: String) {
//    BottomAppBar(actions = {
//        items.forEach { item ->
//            BottomNavigationItem(
//                icon = {
//                    Icon(
//                        imageVector = item.icon,
//                        contentDescription = item.name
//                    )
//                },
//                label = {
//                    Text(text = item.name)
//                },
//                selected = selectedScreen == item.route,
//                onClick = {
//                    onItemClick(item.route)
//                }
//            )
//        }
//    },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { /* do something */ },
//                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
//                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
//            ) {
//                Icon(Icons.Filled.Add, "Localized description")
//            }
//        })
//}

//@Composable
//fun BottomNavigationItem(
//    icon: @Composable () -> Unit,
//    label: @Composable () -> Unit,
//    selected: Boolean,
//    onClick: () -> Unit
//) {
//    IconButton(onClick = {}) {
//        Column {
//            icon()
//        }
//    }
//}
