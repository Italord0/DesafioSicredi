package com.example.desafiosicredi.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.desafiosicredi.nav.NavigationComponent
import com.example.desafiosicredi.ui.theme.DesafioSicrediTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            DesafioSicrediTheme {
                Scaffold { padding ->
                    NavigationComponent(navController, padding)
                }
            }
        }
    }
}