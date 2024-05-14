package com.example.hospedafcil.ui.theme.hospedaFacil.ui

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.room.Room
import com.example.hospedafcil.R
import com.example.hospedafcil.data.AppViewModel
import com.example.hospedafcil.data.BaseDeDatos
import com.example.hospedafcil.ui.theme.apartamento.ui.ApartamentoScreen
import com.example.hospedafcil.ui.theme.casa.ui.ViviendasScreen
import com.example.hospedafcil.ui.theme.habitacion.ui.HabitacionScreen
import com.example.hospedafcil.ui.theme.home.ui.HomeScreen
import com.example.hospedafcil.ui.theme.horario.ui.HorarioScreen
import com.example.hospedafcil.ui.theme.inventario.ui.InventarioScreen
import com.example.hospedafcil.ui.theme.nota.ui.NotaScreen

enum class Screens {
    Home,
    Casas,
    Apartamentos,
    Habitaciones,
    Inventario,
    Notas,
    Horario,
    ViviendaDetail
}

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
    val appViewModel = AppViewModel(viviendaDao)

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screens.valueOf(
        backStackEntry?.destination?.route ?: Screens.Home.name
    )
    Scaffold (
        topBar = { HospedaTopAppBar(modifier = Modifier.fillMaxWidth(), title = currentScreen.name, canNavigateBack = false, navController = navController)},
        bottomBar = { HospedaBottomAppBar(Modifier.fillMaxWidth(), navController) }
        ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
            ){
            composable(route = Screens.Home.name){
                HomeScreen(navController, appViewModel)
            }
            composable(route = Screens.Casas.name){
                ViviendasScreen(viewModel = appViewModel)
            }
            composable(route = Screens.Apartamentos.name){
                ApartamentoScreen()
            }
            composable(route = Screens.Habitaciones.name){
                HabitacionScreen()
            }
            composable(route = Screens.Inventario.name){
                InventarioScreen()
            }
            composable(route = Screens.Notas.name){
                NotaScreen()
            }
            composable(route = Screens.Horario.name){
                HorarioScreen()
            }
            composable(route = Screens.ViviendaDetail.name){
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospedaTopAppBar(
    modifier: Modifier,
    title: String,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navController: NavHostController = rememberNavController(),
    canNavigateBack: Boolean
){
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
                onClick = { navController.navigate(Screens.Home.name) ; expanded = !expanded }
            )
            DropdownMenuItem(
                text = { Text(text = "Casas") },
                onClick = { navController.navigate(Screens.Casas.name) ; expanded = !expanded }
            )
            DropdownMenuItem(
                text = { Text(text = "Apartamentos") },
                onClick = { navController.navigate(Screens.Apartamentos.name) ; expanded = !expanded }
            )
            DropdownMenuItem(
                text = { Text(text = "Habitaciones") },
                onClick = { navController.navigate(Screens.Habitaciones.name) ; expanded = !expanded }
            )
            DropdownMenuItem(
                text = { Text(text = "Inventario") },
                onClick = { navController.navigate(Screens.Inventario.name) ; expanded = !expanded }
            )
            DropdownMenuItem(
                text = { Text(text = "Notas") },
                onClick = { navController.navigate(Screens.Notas.name) ; expanded = !expanded }
            )
            DropdownMenuItem(
                text = { Text(text = "Horario") },
                onClick = { navController.navigate(Screens.Horario.name) ; expanded = !expanded }
            )
        }
    }
}

@Composable
fun HospedaBottomAppBar(modifier: Modifier, navController: NavHostController){
    val listItems = listOf(
        NavigationBarItem(
            route = Screens.Notas.name,
            selectedIcono = painterResource(id = R.drawable.baseline_note_24),
            unSelectedIcono = painterResource(id = R.drawable.baseline_note_24)
        ),NavigationBarItem(
            route = Screens.Home.name,
            selectedIcono = painterResource(id = R.drawable.baseline_home_filled_24),
            unSelectedIcono = painterResource(id = R.drawable.baseline_home_filled_24)
        ),
        NavigationBarItem(
            route = Screens.Horario.name,
            selectedIcono = painterResource(id = R.drawable.baseline_date_range_24),
            unSelectedIcono = painterResource(id = R.drawable.baseline_date_range_24)
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