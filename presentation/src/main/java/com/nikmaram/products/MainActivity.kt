package com.nikmaram.products

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.nikmaram.products.navigation.AppNavigation
import com.nikmaram.products.ui.theme.ProductsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                }
            }
        }
    }
}