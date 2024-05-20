package com.example.hospedafcil.ui.inventarioComponents

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hospedafcil.data.tablas.Item

@Composable
fun InventarioContent(
    padding: PaddingValues,
    inventario: List<Item>,
    deleteItem: (item: Item) -> Unit,
    navigateToUpdateItem: (itemId: Int) -> Unit
){
    LazyColumn (modifier = Modifier
        .fillMaxSize()
        .padding(padding)
    ) {
        items(inventario){ item ->
            ItemCard(
                item = item,
                deleteItem = { deleteItem(item) },
                navigateToUpdateItem = { navigateToUpdateItem(item.id) }
            )
        }
    }
}