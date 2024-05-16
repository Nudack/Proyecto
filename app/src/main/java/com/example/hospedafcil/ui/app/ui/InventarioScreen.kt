package com.example.hospedafcil.ui.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InventarioScreen(){
    Column {
        LazyColumn {

        }
        IconButton(
            onClick = {  },
            modifier = Modifier
            .align(alignment = Alignment.End)
            .padding(16.dp),

        ) {
            Icon(imageVector = Icons.Outlined.Add, contentDescription = "crear objeto en inventario")
        }
    }
}

