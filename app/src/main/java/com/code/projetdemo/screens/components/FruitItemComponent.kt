package com.code.projetdemo.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.code.projetdemo.model.FruitItem
import com.code.projetdemo.ui.theme.greenColor

@Composable
fun ItemCard(item: FruitItem, onFruitClicked: (FruitItem) -> Unit) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .clickable { onFruitClicked(item) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {


            Card(

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.White
                )
            ) {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(90.dp)
                ) {
                Image(
                    painter = rememberAsyncImagePainter(item.imageResource),
                    contentDescription = item.name,
                    modifier = Modifier
                        .wrapContentWidth()
                        .clickable { onFruitClicked(item) }
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                NotifIcon(modifier = Modifier.align(Alignment.TopEnd))
            }



        }


        Text(text = item.name)

    }
}

@Composable
fun NotifIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(color = greenColor, shape = CircleShape)
            .size(14.dp)
            .border(width = 1.dp, color = Color.White, shape = CircleShape)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "1",
            color = Color.White,
            style = TextStyle(fontSize = 8.sp)
        )
    }
}
