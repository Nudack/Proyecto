package com.example.hospedafcil.ui.viviendasScreens.componentes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hospedafcil.data.vivienda.Vivienda

@Composable
fun ViviendasContent(
    padding: PaddingValues,
    viviendas: List<Vivienda>,
    deleteVivienda: (vivienda: Vivienda) -> Unit,
    navigateToUpdateVivienda: (viviendaId: Int) -> Unit
){
    LazyColumn (modifier = Modifier
        .fillMaxSize()
        .padding(padding)
    ) {
        items(viviendas){ vivienda ->
            ViviendasCard(
                vivienda = vivienda,
                deleteVivienda = { deleteVivienda(vivienda) },
                navigateToUpdateVivienda = { navigateToUpdateVivienda(vivienda.id) }
            )
        }
    }
}