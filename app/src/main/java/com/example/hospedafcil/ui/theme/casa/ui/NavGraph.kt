package com.example.hospedafcil.ui.theme.casa.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = ViviendaScreen.route
    ){
        composable( route = ViviendaScreen.route
        ){
            ViviendasScreen()
        }
    }
}