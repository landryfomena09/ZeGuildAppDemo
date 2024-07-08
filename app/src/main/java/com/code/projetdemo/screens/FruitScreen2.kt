package com.code.projetdemo.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ahmadhamwi.tabsync_compose.lazyListTabSync
import com.code.projetdemo.model.FruitItem
import com.code.projetdemo.screens.components.FruitDetail
import com.code.projetdemo.screens.components.MyLazyList
import com.code.projetdemo.screens.components.MyTabBar
import kotlinx.coroutines.launch
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitScreen2(viewModel: FruitViewModel2) {

    LaunchedEffect(key1 = "FruitCategory") {
        viewModel.createData()
    }

    val categoriesState = viewModel.categories.observeAsState()

    val categories = categoriesState.value ?: emptyList()
    val modalBottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var selecteItem :FruitItem? = null

    var selectedTabIndexx by remember { mutableStateOf(0) }
    var selectedTabIndexToDisplay by remember { mutableStateOf(0) }

    if (categories.isNotEmpty()) {

        val (selectedTabIndex, setSelectedTabIndex, listState) = lazyListTabSync(categories.indices.toList(), smoothScroll = true)

        Scaffold(modifier = Modifier.fillMaxSize()) { padding ->

            Column(modifier = Modifier.padding(padding)) {
                MyTabBar(
                    categories = categories,
                    selectedTabIndex = selectedTabIndex-3,
                    onTabClicked = { index, _ -> setSelectedTabIndex(index) }, onItemSelect = {
                        selectedTabIndexx = it
                        scope.launch {
                            listState.animateScrollToItem(it)
                        }
                    }
                )

                MyLazyList(categories, listState, selectedTabIndex-3,onFruitClicked = { fruitItem ->
                    scope.launch {
                        selecteItem = fruitItem
                        modalBottomSheetState.show()
                    }
                })

            }
            if (modalBottomSheetState.isVisible) {
                ModalBottomSheet(
                    onDismissRequest = {
                        scope.launch {
                            modalBottomSheetState.hide()
                        }
                    },
                    sheetState = modalBottomSheetState,
                    dragHandle = { BottomSheetDefaults.DragHandle() },
                ) {
                    selecteItem?.let { itemNotNull ->
                        FruitDetail(fruitItem = itemNotNull, onCloseClicked = {
                            scope.launch {
                                modalBottomSheetState.hide()
                            }
                            viewModel.deleteItem(itemNotNull)

                        }, onArchiveClick = {
                            viewModel.deleteItem(itemNotNull)
                            scope.launch {
                                modalBottomSheetState.hide()
                            }
                        },
                            onDeleteClicked = {
                                viewModel.deleteItem(itemNotNull)
                                scope.launch {
                                    modalBottomSheetState.hide()
                                }
                            })

                    }
                }
            }


        }
    }}