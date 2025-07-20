package com.example.sevenwindscoffee.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.entities.Product
import com.example.sevenwindscoffee.R
import com.example.sevenwindscoffee.presentation.CoffeeUIState
import com.example.sevenwindscoffee.presentation.ViewModel
import com.example.sevenwindscoffee.ui.theme.AntiqueWhite
import com.example.sevenwindscoffee.ui.theme.DarkWood
import com.example.sevenwindscoffee.ui.theme.Sandrift
import com.yandex.mapkit.logo.HorizontalAlignment

@Composable
fun BasketScreen(viewModel: ViewModel, onBackPressed:() -> Unit) {
    val viewState = viewModel.productsState.collectAsStateWithLifecycle()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 30.dp, start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.SpaceBetween) {
        TopBar(stringResource(R.string.basketHeader), backPressed = {onBackPressed()})
        when (val state = viewState.value) {
            is CoffeeUIState.Loading -> LoadingScreen()
            is CoffeeUIState.SuccessProducts -> {
                LazyColumn() {
                    items(state.locations) {
                        if(it.count > 0)
                        {
                           CardProduct(it)
                        }
                    }
                }
                if (state.locations.isNotEmpty())
                {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Text(text = stringResource(R.string.basketText1),  fontSize = 20.sp, color = DarkWood,
                            modifier = Modifier.align(Alignment.CenterHorizontally), fontWeight = FontWeight.Bold
                        )
                        Text(text = stringResource(R.string.basketText2),  fontSize = 20.sp, color = DarkWood,
                            modifier = Modifier.align(Alignment.CenterHorizontally), fontWeight = FontWeight.Bold
                        )
                        Text(text = stringResource(R.string.basketText3),  fontSize = 20.sp, color = DarkWood,
                            modifier = Modifier.align(Alignment.CenterHorizontally), fontWeight = FontWeight.Bold
                        )
                    }
                    Button(stringResource(R.string.basketButton), {})
                }
            }
            is CoffeeUIState.Error -> ErrorScreen(state.message)
            else -> {}
        }
    }
}

@Composable
fun CardProduct(product: Product)
{
    val count = remember { mutableIntStateOf(product.count) }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 3.dp)
        .requiredHeight(70.dp)
        .shadow(elevation = 8.dp)
        .clip(RoundedCornerShape(3.dp)),
        colors = CardDefaults.cardColors(
            contentColor = DarkWood,
            containerColor = AntiqueWhite
        ))
    {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Column(modifier = Modifier.padding(15.dp).weight(1f))
            {
                Text(text = product.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = product.price.toString() + " руб.", fontSize = 15.sp)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp).weight(2f), horizontalArrangement = Arrangement.SpaceEvenly)
            {
                Text(
                    text = "-",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Sandrift,
                    modifier = Modifier.clickable(onClick = {
                        if (product.count > 0) {
                            product.count--
                            count.intValue--
                        }
                    })
                )
                Text(
                    text = count.intValue.toString(),
                    fontSize = 19.sp,
                    color = DarkWood
                )
                Text(
                    text = "+",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Sandrift,
                    modifier = Modifier.clickable(onClick = {
                        count.intValue++
                        product.count++
                    })
                )
            }
        }
    }
}