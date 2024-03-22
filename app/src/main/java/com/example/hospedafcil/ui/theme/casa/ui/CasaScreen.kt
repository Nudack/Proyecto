package com.example.hospedafcil.ui.theme.casa.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hospedafcil.R

@Composable
fun CasaScreen(){
    FilledCardExample()
}

@Composable
fun FilledCardExample() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp)
    ) {
        Column {
            Image(painter = painterResource(id = R.drawable.casa1), contentDescription = "imagen o imagenes de la casa", modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = "Nombre",
                textAlign = TextAlign.Start,
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Text(text = "Descripción")
        }
    }
}