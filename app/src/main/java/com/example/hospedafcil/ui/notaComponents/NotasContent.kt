package com.example.hospedafcil.ui.notaComponents

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hospedafcil.data.tablas.Nota

@Composable
fun NotasContent(
    padding: PaddingValues,
    notas: List<Nota>,
    deleteNota: (nota: Nota) -> Unit,
    navigateToUpdateNota: (notaId: Int) -> Unit
){
    LazyColumn (modifier = Modifier
        .fillMaxSize()
        .padding(padding)
    ) {
        items(notas){ nota ->
            NotaCard(
                nota = nota,
                deleteNota = { deleteNota(nota) },
                navigateToUpdateNota = { navigateToUpdateNota(nota.id) }
            )
        }
    }
}