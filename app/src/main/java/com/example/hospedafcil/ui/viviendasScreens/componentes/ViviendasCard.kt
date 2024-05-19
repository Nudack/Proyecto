package com.example.hospedafcil.ui.viviendasScreens.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hospedafcil.R
import com.example.hospedafcil.data.vivienda.Vivienda

@Composable
fun ViviendasCard(
    vivienda: Vivienda,
    deleteVivienda: () -> Unit,
    navigateToUpdateVivienda: (viviendaId: Int) -> Unit
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
                .padding(12.dp)
                .clickable { navigateToUpdateVivienda(vivienda.id) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                vivienda.imagen?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.size(300.dp)
                )
                }
                Text("Nombre: " + vivienda.nombre, fontSize = 25.sp)
                Text("Descripción", fontSize = 25.sp )
                Text(vivienda.descripcion)

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    IconButton(onClick = { navigateToUpdateVivienda(vivienda.id) }) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null)
                    }
                    IconButton(onClick = { deleteVivienda() }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null)
                    }
                }
            }

        }
    }
}