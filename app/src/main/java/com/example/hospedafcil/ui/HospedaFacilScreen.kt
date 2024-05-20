package com.example.hospedafcil.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.hospedafcil.R
import com.example.hospedafcil.data.AppViewModel
import com.example.hospedafcil.data.BaseDeDatos
import com.example.hospedafcil.ui.inventarioComponents.UpdateItemScreen
import com.example.hospedafcil.ui.notaComponents.UpdateNotaScreen
import com.example.hospedafcil.ui.viviendasScreens.ApartamentoScreen
import com.example.hospedafcil.ui.viviendasScreens.HabitacionScreen
import com.example.hospedafcil.ui.viviendasScreens.CasaScreen
import com.example.hospedafcil.ui.viviendasScreens.HomeScreen
import com.example.hospedafcil.ui.viviendasScreens.componentes.UpdateViviendaScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospedaFacilApp(
    navController: NavHostController = rememberNavController()
){
    val applicationContext = LocalContext.current
    val db = Room.databaseBuilder(
        context = applicationContext,
        BaseDeDatos::class.java,
        "app_database").build()
    val viviendaDao = db.viviendaDao()
    val notaDao = db.notaDao()
    val inventarioDao = db.inventarioDao()
    val appViewModel = AppViewModel(viviendaDao, notaDao, inventarioDao)

    Scaffold (
        topBar = { HospedaTopAppBar(modifier = Modifier.fillMaxWidth(), canNavigateBack = false, navController = navController) },
        bottomBar = { HospedaBottomAppBar(Modifier.fillMaxWidth(), navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Home",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            composable(route = "Home"){
                HomeScreen(
                    viewModel = appViewModel,
                    navController
                )
            }
            composable(route = "Casas"){
                CasaScreen(
                    viewModel = appViewModel,
                    navigateToUpdateVivienda = { viviendaId ->
                        navController.navigate("updateVivienda/$viviendaId")
                    }
                )
            }
            composable(route = "Apartamentos"){
                ApartamentoScreen(
                    viewModel = appViewModel,
                    navigateToUpdateVivienda = { viviendaId ->
                        navController.navigate("updateVivienda/$viviendaId")
                    }
                )
            }
            composable(route = "Habitaciones"){
                HabitacionScreen(
                    viewModel = appViewModel,
                    navigateToUpdateVivienda = { viviendaId ->
                        navController.navigate("updateVivienda/$viviendaId")
                    }
                )
            }
            composable(
                route = "updateVivienda/{viviendaId}",
                arguments = listOf(
                    navArgument("viviendaId"){
                        type = NavType.IntType
                    }
                )
            ) {
                val viviendaId = it.arguments?.getInt("viviendaId") ?: 0
                UpdateViviendaScreen(
                    viewModel = appViewModel,
                    viviendaId = viviendaId,
                    navigateBack = {navController.navigateUp()
                    }
                )
            }
            composable(route = "Inventario"){
                InventarioScreen(
                    viewModel = appViewModel,
                    navigateToUpdateItem = { itemId ->
                        navController.navigate("updateItem/$itemId")
                    }
                )
            }
            composable(
                route = "updateItem/{itemId}",
                arguments = listOf(
                    navArgument("itemId"){
                        type = NavType.IntType
                    }
                )
            ) {
                val itemId = it.arguments?.getInt("itemId") ?: 0
                UpdateItemScreen(
                    viewModel = appViewModel,
                    itemId = itemId,
                    navigateBack = { navController.navigateUp() }
                )
            }
            composable(route = "Notas"){
                NotaScreen(
                    viewModel = appViewModel,
                    navigateToUpdateNota = { notaId ->
                        navController.navigate("updateNota/$notaId")
                    }
                )
            }
            composable(
                route = "updateNota/{notaId}",
                arguments = listOf(
                    navArgument("notaId"){
                        type = NavType.IntType
                    }
                )
            ) {
                val notaId = it.arguments?.getInt("notaId") ?: 0
                UpdateNotaScreen(
                    viewModel = appViewModel,
                    notaId = notaId,
                    navigateBack = { navController.navigateUp() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospedaTopAppBar(
    modifier: Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navController: NavHostController = rememberNavController(),
    canNavigateBack: Boolean
){
    // Get the current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Set the title based on the current destination
    val title = when (backStackEntry?.destination?.route) {
        "Home" -> "Home"
        "Casas" -> "Casas"
        "Apartamentos" -> "Apartamentos"
        "Habitaciones" -> "Habitaciones"
        "Inventario" -> "Inventario"
        "Notas" -> "Notas"
        else -> ""
    }
    var expanded by remember { mutableStateOf(false) }
    CenterAlignedTopAppBar(
        title = { Text(text = title)},
        scrollBehavior = scrollBehavior,
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu de pantallas")
            }
        },
        actions = {
            if (canNavigateBack){
                IconButton(onClick = {navController.navigateUp()}) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            }
        }
    )
    Box(modifier = Modifier
        .wrapContentSize(Alignment.TopStart)
        .padding(top = 51.dp, start = 8.dp)){
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { Text(text = "Home") },
                onClick = { navController.navigate("Home") ; expanded = !expanded }
            )
            DropdownMenuItem(
                text = { Text(text = "Casas") },
                onClick = { navController.navigate("Casas") ; expanded = !expanded }
            )
            DropdownMenuItem(
                text = { Text(text = "Apartamentos") },
                onClick = { navController.navigate("Apartamentos") ; expanded = !expanded }
            )
            DropdownMenuItem(
                text = { Text(text = "Habitaciones") },
                onClick = { navController.navigate("Habitaciones") ; expanded = !expanded }
            )
            DropdownMenuItem(
                text = { Text(text = "Inventario") },
                onClick = { navController.navigate("Inventario") ; expanded = !expanded }
            )
            DropdownMenuItem(
                text = { Text(text = "Notas") },
                onClick = { navController.navigate("Notas") ; expanded = !expanded }
            )
        }
    }
}

@Composable
fun HospedaBottomAppBar(modifier: Modifier, navController: NavHostController){
    val listItems = listOf(
        NavigationBarItem(
            route = "Notas",
            selectedIcono = painterResource(id = R.drawable.baseline_note_24),
            unSelectedIcono = painterResource(id = R.drawable.outline_note_24)
        ), NavigationBarItem(
            route = "Home",
            selectedIcono = painterResource(id = R.drawable.baseline_home_filled_24),
            unSelectedIcono = painterResource(id = R.drawable.outline_home_24)
        ),
        NavigationBarItem(
            route = "Inventario",
            selectedIcono = painterResource(id = R.drawable.baseline_inventory_24),
            unSelectedIcono = painterResource(id = R.drawable.outline_inventory_2_24)
        )
    )
    NavigationBar (modifier = modifier) {
        listItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painter = item.selectedIcono, contentDescription = "icono") },
                label = { Text(item.route) },
                selected = false,
                onClick = { navController.navigate(item.route) }
            )
        }
    }
}

data class NavigationBarItem(
    val route: String,
    val selectedIcono: Painter,
    val unSelectedIcono: Painter,
)