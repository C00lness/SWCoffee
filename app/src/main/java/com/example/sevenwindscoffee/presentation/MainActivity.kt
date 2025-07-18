package com.example.sevenwindscoffee.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sevenwindscoffee.screens.homeScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

//Не стал выделять в отдельный модуль presentation, чистота от этого, думаю, не пострадает
class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<ViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            homeScreen(viewModel)
        }
    }
}