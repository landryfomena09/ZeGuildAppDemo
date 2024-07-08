package com.code.projetdemo.model

import java.util.Date

class FruitCategory(val date: Date, val item: MutableList<FruitItem>) {
    val listOfItems: List<FruitItem> = item.toList()
}