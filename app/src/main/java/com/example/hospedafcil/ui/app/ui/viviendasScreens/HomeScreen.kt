package com.example.hospedafcil.ui.app.ui.viviendasScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hospedafcil.R
import com.example.hospedafcil.data.AppViewModel
import com.example.hospedafcil.data.vivienda.Vivienda
import kotlinx.coroutines.flow.Flow
import androidx.compose.ui.res.vectorResource

@Composable
fun HomeScreen(
    viewModel: AppViewModel,
    navigateToUpdateVivienda: (viviendaId: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Casas", textAlign = TextAlign.Start)
        CarouselCard(viewModel.casas, navigateToUpdateVivienda)

        Spacer(modifier = Modifier.padding(16.dp))

        Text(text = "Apartamentos")
        CarouselCard(viewModel.apartamentos, navigateToUpdateVivienda)

        Spacer(modifier = Modifier.padding(16.dp))

        Text(text = "Habitaciones")
        CarouselCard(viewModel.habitaciones, navigateToUpdateVivienda)
    }
}

@Composable
fun CarouselCard(
    viviendas: Flow<List<Vivienda>>,
    navigateToUpdateVivienda: (viviendaId: Int) -> Unit
) {
    val viviendasState = viviendas.collectAsState(initial = emptyList())

    LazyRow(modifier = Modifier.height(300.dp)) {
        items(viviendasState.value) { vivienda ->
            Column(
                modifier = Modifier
                    .clickable {
                navigateToUpdateVivienda(vivienda.id)
                }
                    .padding(end = 8.dp, start = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (vivienda.imagen == null){
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_image_not_supported_24),
                        contentDescription = null,
                        modifier = Modifier.size(250.dp)
                    )
                }
                else{
                    Image(
                        bitmap = vivienda.imagen.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.size(250.dp)
                    )
                }
                Text(text = vivienda.nombre)
            }
        }
    }
}