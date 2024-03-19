package com.example.hospedafcil.ui.theme.home.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hospedafcil.R


//hay que cambiar lo que tengo por lo que sale en el video: https://www.youtube.com/watch?v=XyEOxwkObJc
//porque a el le salen bien las imagenes y si sale igual cambiar el la resolucion de las imagenes
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarouselCard(){
    val pagerState = rememberPagerState(pageCount = { 3 })
    val sliderList = listOf(
        R.drawable.casa1,
        R.drawable.casa2,
        R.drawable.casa3
    )
    Column () {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.height(350.dp).align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {page ->
            Card (shape = RoundedCornerShape(10.dp)) {
                Image(
                    painter = painterResource(id = sliderList[page]),
                    modifier = Modifier.size(350.dp),
                    contentDescription = null
                )
            }
        }
    }
}