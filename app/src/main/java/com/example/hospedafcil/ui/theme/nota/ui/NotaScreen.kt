package com.example.hospedafcil.ui.theme.nota.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hospedafcil.R

val notas = listOf(
    Nota(R.drawable.casa1, "Prueba1", "descipcion de prueba para la lista de notas"),
    Nota(R.drawable.casa2, "Prueba1", "descipcion de prueba para la lista de notas"),
    Nota(R.drawable.casa3, "Prueba1", "descipcion de prueba para la lista de notas"),
    Nota(R.drawable.casa1, "Prueba1", "descipcion de prueba para la lista de notas"),
    Nota(R.drawable.casa2, "Prueba1", "descipcion de prueba para la lista de notas"),
    Nota(R.drawable.casa3, "Prueba1", "descipcion de prueba para la lista de notas")
)

@Composable
fun NotaScreen(){
    Notas(notas)
}

@Composable
fun Notas (notas: List<Nota>) {
    LazyColumn {
        items(items = notas) { cadaNota->
            Row (modifier = Modifier.height(60.dp).padding(top = 8.dp, bottom = 8.dp)) {
                Image(
                    painter = painterResource(id = cadaNota.imagePath),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(50.dp)
                        .padding(end = 2.dp)

                )

                Column{
                    Text(text = cadaNota.nomVivenda)
                    Text(text = cadaNota.descripcion)
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                }
            }
        }
    }
}