package com.example.hospedafcil.ui.theme.inventario.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InventarioScreen(){
    LazyColumn {
        
    }

    IconButton(onClick = {  }, modifier = Modifier
        .wrapContentSize(Alignment.BottomEnd)
        .padding(16.dp)) {
        Icon(imageVector = Icons.Outlined.Add, contentDescription = "crear objeto en inventario")
    }
}

