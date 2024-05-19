package com.example.hospedafcil.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.hospedafcil.data.AppViewModel
import com.example.hospedafcil.data.nota.Nota
import kotlinx.coroutines.flow.Flow


@Composable
fun NotaScreen(viewModel: AppViewModel){
    Notas(viewModel.notas, viewModel)
}

@Composable
fun Notas (notas: Flow<List<Nota>>, viewModel: AppViewModel) {
    val notasState = notas.collectAsState(initial = emptyList())
    LazyColumn {
        items(items = notasState.value) { cadaNota->
            LaunchedEffect(key1 = Unit) {
                viewModel.getVivienda(cadaNota.idVivienda)
            }
            val vivienda = viewModel.vivienda
            Row (modifier = Modifier
                .height(60.dp)
                .padding(top = 8.dp, bottom = 8.dp)) {
                Image(
                    bitmap = vivienda.imagen?.asImageBitmap()!!,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                        .padding(end = 2.dp)

                )

                Column{
                    Text(text = vivienda.nombre)
                    Text(text = cadaNota.descripcion)
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                }
            }
        }
    }
}