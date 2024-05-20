package com.example.hospedafcil.ui.inventarioComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun AddItemFloatingActionButton(
    openItemDialog: () -> Unit
) {
    FloatingActionButton( onClick = { openItemDialog() } ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Añadir Item")
    }
}