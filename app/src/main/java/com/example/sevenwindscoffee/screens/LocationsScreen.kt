package com.example.sevenwindscoffee.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sevenwindscoffee.presentation.CoffeeUIState
import com.example.sevenwindscoffee.presentation.ViewModel
import com.example.sevenwindscoffee.ui.theme.AntiqueWhite
import com.example.sevenwindscoffee.ui.theme.DarkWood

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsScreen(viewModel: ViewModel, onClick:() -> Unit, onBackPressed:() -> Unit, toMap:() -> Unit) {
    TopAppBar(title = {
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center)
        {
            Text(text = "Ближайшие кофейни", fontWeight = FontWeight.Bold)
        }
    },
        navigationIcon={ IconButton({onBackPressed() }) { Icon(Icons.Filled.KeyboardArrowLeft,
            contentDescription = "Назад")}},

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = DarkWood,
            navigationIconContentColor = DarkWood))

    val viewState = viewModel.locationsState.collectAsStateWithLifecycle()
    Column(modifier = Modifier.padding(25.dp, 100.dp)) {
        when (val state = viewState.value) {
            is CoffeeUIState.Loading -> LoadingScreen()
            is CoffeeUIState.SuccessLocations ->
                Column()
                {
                    LazyColumn(modifier = Modifier.padding(bottom = 50.dp)) {
                        items(state.locations){
                            CardLocation(it.name.toString(), onClick = {
                                viewModel.getProducts("/location/" + it.id + "/menu", viewModel.tokenState.value.toString())
                                onClick()
                            })
                        }
                    }
                    Button("На карте", onClick = {toMap()})
                }

            is CoffeeUIState.Error -> ErrorScreen(state.message)
            else -> {}
        }
    }
}

@Composable
fun CardLocation(name: String, onClick: () -> Unit)
{
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 3.dp)
        .requiredHeight(70.dp)
        .clip(RoundedCornerShape(3.dp))
        .clickable {onClick()},
        colors = CardDefaults.cardColors(
            contentColor = DarkWood,
            containerColor = AntiqueWhite))
    {
        Box(modifier = Modifier.fillMaxSize().padding(15.dp))
        {
            Text(text = name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}