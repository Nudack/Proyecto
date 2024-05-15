package com.example.hospedafcil.ui.app.ui.viviendasScreens

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hospedafcil.data.AppViewModel
import com.example.hospedafcil.data.vivienda.Vivienda
import com.example.hospedafcil.ui.app.ui.ui.ui.Screens
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: AppViewModel
) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Casas", textAlign = TextAlign.Start)
        CarouselCard(viewModel.casaList, "Casa", navController)

        Spacer(modifier = Modifier.padding(16.dp))

        Text(text = "Apartamentos")
        CarouselCard(viewModel.apartamentoList, "Apartamento", navController)

        Spacer(modifier = Modifier.padding(16.dp))

        Text(text = "Habitaciones")
        CarouselCard(viewModel.habitacionList, "Habitación", navController)
    }
}

@Composable
fun CarouselCard(
    viviendas: Flow<List<Vivienda>>,
    tipoVivienda: String,
    navController: NavHostController
) {
    val viviendasState = viviendas.collectAsState(initial = emptyList())

    LazyRow(modifier = Modifier.height(300.dp)) {
        items(viviendasState.value) { vivienda ->
            Column {
                Text(text = tipoVivienda, fontSize = 25.sp)
                Image(
                    bitmap = vivienda.imagen?.asImageBitmap()!!,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        navController.navigate(Screens.ViviendaDetail.name + "/${vivienda.id}")
                    }
                )
                Text(text = vivienda.nombre)
            }
        }
    }
}