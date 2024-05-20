package com.example.hospedafcil.ui.notaComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun AddNotaFloatingActionButton(
    openNotaDialog: () -> Unit
) {
    FloatingActionButton( onClick = { openNotaDialog() } ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Añadir Nota")
    }
}