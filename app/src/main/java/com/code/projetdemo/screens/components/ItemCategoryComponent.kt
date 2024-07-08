package com.code.projetdemo.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.code.projetdemo.R
import com.code.projetdemo.model.FruitCategory
import com.code.projetdemo.model.FruitItem
import com.code.projetdemo.utils.convertDateToSpecificStringFormat

@Composable
fun ItemCategoryComponent(
    category: FruitCategory,
    onSelected:Boolean,
    onFruitClicked: (FruitItem) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(category.date.convertDateToSpecificStringFormat("dd MMM yyyy"), fontSize = 18.sp, color = if (onSelected) Color.Green else Color.Black)

        Spacer(Modifier.height(8.dp))

 /*       Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Absolute.Left) {
            Column {
                category.listOfItems .forEach {
                    ItemCard(item = it, onFruitClicked = onFruitClicked)
                    Spacer(modifier = Modifier.height(6.dp))
                }
        }}
*/
        LazyRow {
            items(category.listOfItems.size) { fruit ->
                ItemCard(item = category.listOfItems[fruit], onFruitClicked = onFruitClicked)
                Spacer(modifier = Modifier.width(6.dp))
            }
        }

        if (category.listOfItems.isEmpty())
            Text(text = stringResource(R.string.pas_de_produit))

    }
}
