package com.code.projetdemo.screens.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import com.code.projetdemo.model.FruitCategory
import com.code.projetdemo.model.FruitItem

@Composable
fun MyLazyList(
    categories: List<FruitCategory>,
    listState: LazyListState = rememberLazyListState(),
    selectedDay: Int,
    onFruitClicked:(FruitItem) -> Unit
) {
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(categories) { index, category ->
            ItemCategoryComponent(category,selectedDay==index, onFruitClicked = onFruitClicked)
        }
    }


}
