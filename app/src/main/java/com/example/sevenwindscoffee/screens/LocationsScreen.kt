package com.example.sevenwindscoffee.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sevenwindscoffee.presentation.CoffeeUIState
import com.example.sevenwindscoffee.presentation.ViewModel
import com.example.sevenwindscoffee.ui.theme.AntiqueWhite
import com.example.sevenwindscoffee.ui.theme.DarkWood
import com.example.sevenwindscoffee.R

@Composable
fun LocationsScreen(viewModel: ViewModel, onClick:() -> Unit, onBackPressed:() -> Unit, toMap:() -> Unit) {
    val viewState = viewModel.locationsState.collectAsStateWithLifecycle()
    Column(modifier = Modifier.fillMaxSize().padding(bottom = 100.dp, start = 10.dp, end = 10.dp, top = 10.dp),
        verticalArrangement = Arrangement.SpaceEvenly)
    {
        TopBar(stringResource(id = R.string.locationHeader), backPressed = {onBackPressed()})
        when (val state = viewState.value) {
            is CoffeeUIState.Loading -> LoadingScreen()
            is CoffeeUIState.SuccessLocations ->
            {
                LazyColumn(modifier = Modifier.padding(bottom = 50.dp)) {
                    items(state.locations) {
                        CardLocation(
                            it.name.toString(),
                            it.s.toString(),
                            onClick = {
                                viewModel.getProducts(
                                    "/location/" + it.id + "/menu",
                                    viewModel.tokenState.value.toString()
                                )
                                onClick()
                            })
                    }
                }
                Button(stringResource(R.string.locationButton), onClick = { toMap() })
            }
            is CoffeeUIState.Error -> ErrorScreen(state.message)
            else -> {}
        }
    }
}

@Composable
fun CardLocation(name: String, distance: String, onClick: () -> Unit)
{
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 5.dp)
        .requiredHeight(70.dp)
        .shadow(elevation = 3.dp)
        .clip(RoundedCornerShape(3.dp))
        .clickable {onClick()},
        colors = CardDefaults.cardColors(
            contentColor = DarkWood,
            containerColor = AntiqueWhite))
    {
        Column(modifier = Modifier.padding(15.dp))
        {
            Text(text = name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = distance + " км.", fontSize = 10.sp)
        }
    }
}