package com.pepinho.pmdm.materialdesign.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarsScreen(
//    navController: NavController
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Top Bars", "Bottom Bars", "Combinación")

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Ejemplos App Bars") },
                navigationIcon = {
                    IconButton(onClick = {
//                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver")
                    }
                }
            )
        },
        bottomBar = {
            if (selectedTab == 2) {
                BottomAppBarEjemploCompleto()
            } else {
                TabRow(
                    selectedTabIndex = selectedTab,
                    divider = {
                        HorizontalDivider(thickness = 1.dp,
                            color = MaterialTheme.colorScheme.outlineVariant
                        )
                    },
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                             color = MaterialTheme.colorScheme.primary
                        )
                    }
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) }
                        )
                    }
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedTab) {
                0 -> TopAppBarsEjemplos()
                1 -> BottomAppBarsEjemplos()
                2 -> CombinacionAppBarsEjemplo()
            }
        }
    }
}

// 1. Ejemplos de Top App Bars
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarsEjemplos() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Small
        Text("Small App Bar", style = MaterialTheme.typography.titleMedium)
        TopAppBar(
            title = { Text("Título pequeño") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Center Aligned
        Text("Center Aligned", style = MaterialTheme.typography.titleMedium)
        CenterAlignedTopAppBar(
            title = { Text("Centrado") },
            actions = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Search, "Buscar")
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Medium con Scroll
        Text("Medium con Scroll", style = MaterialTheme.typography.titleMedium)
        val mediumScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
            rememberTopAppBarState()
        )
        Box(
            modifier = Modifier
                .height(200.dp)
                .nestedScroll(mediumScrollBehavior.nestedScrollConnection)
        ) {
            MediumTopAppBar(
                title = { Text("Scroll dinámico") },
                scrollBehavior = mediumScrollBehavior
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Large con acciones
        Text("Large con acciones", style = MaterialTheme.typography.titleMedium)
        LargeTopAppBar(
            title = { Text("Título grande") },
            actions = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Favorite, "Favoritos")
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Share, "Compartir")
                }
            }
        )
    }
}

// 2. Ejemplos de Bottom App Bars
@Composable
fun BottomAppBarsEjemplos() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Simple con iconos
        Text("Bottom Bar básico", style = MaterialTheme.typography.titleMedium)
        Box(modifier = Modifier.height(80.dp)) {
            BottomAppBar {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Home, "Inicio")
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Settings, "Ajustes")
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        // Con FAB integrado
        Text("Con FAB", style = MaterialTheme.typography.titleMedium)
        Box(modifier = Modifier.height(80.dp)) {
            BottomAppBar(
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Email, "Mensajes")
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {}) {
                        Icon(Icons.Filled.Add, "Añadir")
                    }
                }
            )
        }
    }
}

// 3. Combinación completa
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CombinacionAppBarsEjemplo() {
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text("Pantalla Completa") },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Search, "Buscar")
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {},
                icon = { Icon(Icons.Filled.Create, "Crear") },
                text = { Text("Nuevo") }
            )
        }
    ) { padding ->
        LazyColumn(
            state = scrollState,
            contentPadding = padding
        ) {
            items(50) { index ->
                ListItem(
                    headlineContent = { Text("Elemento $index") },
                    leadingContent = {
                        Icon(Icons.Filled.List, null)
                    }
                )
                Divider()
            }
        }
    }
}

// Componente reutilizable de BottomAppBar
@Composable
fun BottomAppBarEjemploCompleto() {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Home, "Inicio")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Favorite, "Favoritos")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Person, "Perfil")
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(Icons.Filled.Add, "Añadir")
            }
        }
    )
}