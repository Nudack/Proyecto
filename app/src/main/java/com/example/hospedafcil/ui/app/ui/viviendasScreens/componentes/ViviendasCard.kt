package com.example.hospedafcil.ui.app.ui.viviendasScreens.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hospedafcil.data.vivienda.Vivienda

@Composable
fun ViviendasCard(
    vivienda: Vivienda
){
    Card (
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Nombre: " + vivienda.nombre, fontSize = 25.sp)
                vivienda.imagen?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = null
                    )
                }
                Text("Tipo: " + vivienda.tipo, fontSize = 25.sp)
                Text("Descripción: " + vivienda.descripcion, fontSize = 25.sp)
            }

        }
    }
}