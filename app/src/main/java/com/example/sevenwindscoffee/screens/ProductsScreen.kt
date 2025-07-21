package com.example.sevenwindscoffee.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import com.example.sevenwindscoffee.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sevenwindscoffee.presentation.CoffeeUIState
import com.example.sevenwindscoffee.presentation.ViewModel

@Composable
fun ProductsScreen(viewModel: ViewModel, onClick:() -> Unit, onBackPressed:() -> Unit) {
    val viewState = viewModel.productsState.collectAsStateWithLifecycle()
    Column(modifier = Modifier.fillMaxSize().padding(bottom = 100.dp, start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.SpaceBetween) {
        TopBar(stringResource(R.string.productHeader), backPressed = {onBackPressed()})
        when (val state = viewState.value) {
            is CoffeeUIState.Loading -> LoadingScreen()
            is CoffeeUIState.SuccessProducts ->
            {
                GridScreen(state.locations)
                if(state.locations.isNotEmpty()) Button(stringResource(R.string.productButton), onClick = {onClick()})
            }

            is CoffeeUIState.Error -> ErrorScreen(state.message)
            else -> {}
        }
    }
}