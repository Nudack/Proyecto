package com.example.hospedafcil.ui.theme.hospedaFacil.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.hospedafcil.ui.theme.hospedaFacil.viewModelHF.HospedaViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hospedafcil.R
import com.example.hospedafcil.ui.theme.apartamento.ui.ApartamentoScreen
import com.example.hospedafcil.ui.theme.casa.ui.CasaScreen
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
    Horario
}

@Composable
fun HospedaFacilApp(
    viewModel: HospedaViewModel,
    navController: NavHostController = rememberNavController()
    ){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screens.valueOf(
        backStackEntry?.destination?.route ?: Screens.Home.name
    )

    Scaffold (topBar = { HospedaTopAppBar(Modifier.fillMaxWidth(), currentScreen) },
        bottomBar = { HospedaBottomAppBar(Modifier.fillMaxWidth(), navController) }
        ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
            ){
            composable(route = Screens.Home.name){
                HomeScreen()
            }
            composable(route = Screens.Casas.name){
                CasaScreen()
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
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospedaTopAppBar(modifier: Modifier, currentScreen: Screens){
    CenterAlignedTopAppBar(
        title = { Text(text = currentScreen.name) },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu de pantallas")
            }
        },
        actions = {
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Usuario")
            }
        }
    )
}

@Composable
fun HospedaBottomAppBar(modifier: Modifier, navController: NavHostController){
    var selectedItem by remember { mutableIntStateOf(0) }
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
        listItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(painter = item.selectedIcono, contentDescription = "icono") },
                label = { Text(item.route) },
                selected = selectedItem == index,
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

