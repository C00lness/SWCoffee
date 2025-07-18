package com.example.sevenwindscoffee.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sevenwindscoffee.presentation.CoffeeUIState
import com.example.sevenwindscoffee.presentation.ViewModel
import com.example.sevenwindscoffee.ui.theme.DarkWood

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(viewModel: ViewModel, onClick:() -> Unit, onBackPressed:() -> Unit) {
    TopAppBar(title = {
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center)
        {
            Text(text = "Меню", fontWeight = FontWeight.Bold)
        }
    },
        navigationIcon = {
            IconButton({onBackPressed() }) {
                Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "Назад")
        }},

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = DarkWood,
            navigationIconContentColor = DarkWood
        ))

    val viewState = viewModel.productsState.collectAsStateWithLifecycle()
    Column(modifier = Modifier.padding(25.dp, 100.dp)) {
        when (val state = viewState.value) {
            is CoffeeUIState.Loading -> LoadingScreen()
            is CoffeeUIState.SuccessProducts ->
                Column()
                {
                    GridScreen(state.locations)
                    Button("Перейти к оплате", {})
                }

            is CoffeeUIState.Error -> ErrorScreen(state.message)
            else -> {}
        }
    }
}