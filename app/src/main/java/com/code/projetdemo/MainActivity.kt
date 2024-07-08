package com.code.projetdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.code.projetdemo.screens.FruitScreen2
import com.code.projetdemo.screens.FruitViewModel2
import com.code.projetdemo.ui.theme.ProjetDemoTheme

class MainActivity : ComponentActivity() {

    //private val myViewModel by viewModels<FruitViewModel>()
    private val myViewModel by viewModels<FruitViewModel2>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjetDemoTheme {
               //FruitScreen(myViewModel)
               FruitScreen2(myViewModel)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    ProjetDemoTheme {
    }
}