package com.example.hospedafcil.ui.inventarioComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hospedafcil.data.tablas.Item

@Composable
fun ItemCard (
    item: Item,
    deleteItem: () -> Unit,
    navigateToUpdateItem: (itemId: Int) -> Unit
){
    Card (
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(
                start = 25.dp,
                end = 25.dp,
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
                .clickable { navigateToUpdateItem(item.id) },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                Text("Nombre: " + item.nombre, fontSize = 20.sp)
                Text("Cantidad: " + item.cantidad, fontSize = 20.sp)
                Text("Descripción", fontSize = 20.sp )
                Text(item.descripcion)

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    IconButton(onClick = { navigateToUpdateItem(item.id) }) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null)
                    }
                    IconButton(onClick = { deleteItem() }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null)
                    }
                }
            }
        }
    }
}