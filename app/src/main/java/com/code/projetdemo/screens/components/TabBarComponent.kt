package com.code.projetdemo.screens.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.code.projetdemo.model.FruitCategory
import com.code.projetdemo.ui.theme.greenColor
import com.code.projetdemo.utils.getDayOfMonthFromDate
import com.code.projetdemo.utils.getDayOfWeek

@Composable
fun MyTabBar(
    categories: List<FruitCategory>,
    selectedTabIndex: Int,
    onTabClicked: (index: Int, category: FruitCategory) -> Unit,
    onItemSelect: (index: Int) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp,
        divider = { },
        indicator = {}
    ) {
        categories.forEachIndexed { index, category ->

            val backgroundColor = if (index == selectedTabIndex) {
                greenColor.copy(alpha = 0.1f)
            } else {
                Color.Transparent
            }

            val textColor = if (index == selectedTabIndex) {
                greenColor
            } else {
                Color.LightGray
            }
            Tab(
                selected = index == selectedTabIndex,
                onClick = { onTabClicked(index, category)
                          onItemSelect(index)},
                selectedContentColor = Color.Transparent,
                text = {
                    Box(
                        modifier = Modifier
                            .background(
                                shape = RoundedCornerShape(8.dp),
                                color = backgroundColor
                            )
                            .padding(6.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .wrapContentWidth()
                                .align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                category.date.getDayOfMonthFromDate().toString(),
                                color = textColor, fontWeight = if(selectedTabIndex==index) FontWeight.Bold else FontWeight.Normal
                            )
                            Text(getDayOfWeek(category.date),
                                color = textColor, fontWeight = if(selectedTabIndex==index) FontWeight.Bold else FontWeight.Normal
                            )
                            Row(modifier = Modifier.wrapContentWidth()) {
                                category.listOfItems.forEach {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = textColor,
                                                shape = CircleShape
                                            )
                                            .size(4.dp)
                                    )
                                    Spacer(modifier = Modifier.width(1.dp))
                                }
                            }
                        }
                    }
                })
        }
    }
}
