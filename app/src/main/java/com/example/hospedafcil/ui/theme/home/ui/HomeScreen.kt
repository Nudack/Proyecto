package com.example.hospedafcil.ui.theme.home.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hospedafcil.R
import com.example.hospedafcil.ui.theme.hospedaFacil.ui.Screens

val sliderListCasas = listOf(
    R.drawable.casa1,
    R.drawable.casa2,
    R.drawable.casa3
)

val sliderListApartamentos = listOf(
    R.drawable.casa1,
    R.drawable.casa2,
    R.drawable.casa3
)

val sliderListHabitaciones = listOf(
    R.drawable.casa1,
    R.drawable.casa2,
    R.drawable.casa3
)

@Composable
fun HomeScreen(navController: NavHostController) {
    Column (
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Casas", textAlign = TextAlign.Start)
        CarouselCardCasas(sliderListCasas, navController)

        Spacer(modifier = Modifier.padding(16.dp))

        Text(text = "Apartamentos")
        CarouselCardApartamentos(sliderListApartamentos, navController)

        Spacer(modifier = Modifier.padding(16.dp))

        Text(text = "Habitaciones")
        CarouselCardHabitaciones(sliderListHabitaciones, navController)
    }
}

@Composable
fun CarouselCardCasas(sliderList: List<Int>, navController: NavHostController){
    LazyRow (modifier = Modifier.height(300.dp)) {
        items(sliderList) { imagen ->
            Image(
                painter = painterResource(id = imagen),
                contentDescription = null,
                modifier = Modifier.clickable { navController.navigate(Screens.Casas.name) }
            )
        }
    }
}
@Composable
fun CarouselCardApartamentos(sliderList: List<Int>, navController: NavHostController){
    LazyRow (modifier = Modifier.height(300.dp)) {
        items(sliderList) { imagen ->
            Image(
                painter = painterResource(id = imagen),
                contentDescription = null,
                modifier = Modifier.clickable { navController.navigate(Screens.Apartamentos.name) }
            )
        }
    }
}

@Composable
fun CarouselCardHabitaciones(sliderList: List<Int>, navController: NavHostController){
    LazyRow (modifier = Modifier.height(300.dp)) {
        items(sliderList) { imagen ->
            Image(
                painter = painterResource(id = imagen),
                contentDescription = null,
                modifier = Modifier.clickable { navController.navigate(Screens.Habitaciones.name) }
            )
        }
    }
}


