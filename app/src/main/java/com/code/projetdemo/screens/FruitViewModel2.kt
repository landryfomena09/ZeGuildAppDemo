package com.code.projetdemo.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code.projetdemo.R
import com.code.projetdemo.model.FruitCategory
import com.code.projetdemo.model.FruitItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import java.util.Calendar
import java.util.UUID
import kotlin.random.Random

class FruitViewModel2 : ViewModel() {

    private val _categories = MutableLiveData<List<FruitCategory>>()
    val categories: LiveData<List<FruitCategory>> = _categories

    fun createData() {
        val categories = mutableListOf<FruitCategory>()
        val random = Random

        val calendar:Calendar = Calendar.getInstance()
        calendar.set(2024, Calendar.JULY, 1) // Months are zero-based in Calendar, so July is 6

        for (i in 1..31) { // Assuming we are creating 3 categories
            val items = mutableListOf<FruitItem>()
            for (j in 1..random.nextInt(5) + 3) { // Each category will have 3 to 7 items
                val item = FruitItem(
                    id=UUID.randomUUID().toString(),
                    name = if(j % 2 == 0 ) "Fraise" else "orange",
                    imageResource = if(j % 2 == 0 )R.drawable.fraise else R.drawable.orange , // You can replace this with actual image URLs if needed
                    expirationDate = calendar.time ,
                            detail = if(j % 2 == 0 ) "Fraise detail description  Lorem ipsum is placeholder print," else "orange orange details description, Lorem ipsum is placeholder ,"
                )
                items.add(item)
            }
            val category = FruitCategory(
                date = calendar.time,
                item = items
            )
            categories.add(category)

            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        _categories.value =  categories
    }

    fun deleteItem(itemNotNull: FruitItem) {
        val categories = _categories.value?.map { category ->
            val newItems = category.item.filter { it.id != itemNotNull.id }.toMutableList()
            FruitCategory(category.date, newItems)
        } ?: emptyList()

        // Reassign the value to trigger observers
        _categories.value = categories
    }
}
