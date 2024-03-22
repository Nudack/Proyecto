package com.example.hospedafcil.ui.theme.home.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hospedafcil.R

val sliderListCasas = listOf(
    R.drawable.casa1,
    R.drawable.casa2,
    R.drawable.casa3
)

val sliderListApartamentos = listOf(
    R.drawable.casa1,
    R.drawable.casa2,
    R.drawable.casa3
)

val sliderListHabitaciones = listOf(
    R.drawable.casa1,
    R.drawable.casa2,
    R.drawable.casa3
)

@Composable
fun HomeScreen(){
    Column (modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center) {

        Text(text = "Casas", textAlign = TextAlign.Start)
        CarouselCard(sliderListCasas)

        Spacer(modifier = Modifier.padding(16.dp))

        Text(text = "Apartamentos")
        CarouselCard(sliderListApartamentos)

        Spacer(modifier = Modifier.padding(16.dp))

        Text(text = "Habitaciones")
        CarouselCard(sliderListHabitaciones)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselCard(sliderList: List<Int>){
    val pagerState = rememberPagerState(pageCount = { sliderList.size })

    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .height(300.dp)
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {page ->
            IconButton(onClick = {  }) {
                Image(
                    painter = painterResource(id = sliderList[page]),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null
                )
            }
        }
    }
}