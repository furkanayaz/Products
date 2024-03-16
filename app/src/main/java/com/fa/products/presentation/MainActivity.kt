package com.fa.products.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fa.products.presentation.products.Products
import com.fa.products.ui.theme.HiltTestingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HiltTestingTheme {
                Products()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HiltTestingTheme {
        Products()
    }
}