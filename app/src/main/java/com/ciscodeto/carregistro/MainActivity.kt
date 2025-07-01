package com.ciscodeto.carregistro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ciscodeto.carregistro.cars.presentation.screens.CarsListScreen
import com.ciscodeto.carregistro.ui.theme.CarregistroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarregistroTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CarsListScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}