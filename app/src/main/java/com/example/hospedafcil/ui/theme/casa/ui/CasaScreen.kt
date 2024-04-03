package com.example.hospedafcil.ui.theme.casa.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hospedafcil.R
import com.example.hospedafcil.ui.theme.Carta
import com.example.hospedafcil.ui.theme.casa.viewModel.AppViewModelProvider
import com.example.hospedafcil.ui.theme.casa.viewModel.CasaViewModel

val cartas = listOf(
    Carta(R.drawable.casa1, "Descripcion de ejemplo de la casa 1", "Casa 1"),
    Carta(R.drawable.casa2, "Descripcion de ejemplo de la casa 2", "Casa 2"),
    Carta(R.drawable.casa3, "Descripcion de ejemplo de la casa 3", "Casa 3")
)

@Composable
fun CasaScreen(){
    CasaEntryScreen()
}

