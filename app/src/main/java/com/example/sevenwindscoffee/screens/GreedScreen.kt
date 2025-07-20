package com.example.sevenwindscoffee.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.domain.entities.Product
import com.example.sevenwindscoffee.ui.theme.BlackMagic
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Alignment
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.sevenwindscoffee.R
import com.example.sevenwindscoffee.ui.theme.AntiqueWhite
import com.example.sevenwindscoffee.ui.theme.DarkWood
import com.example.sevenwindscoffee.ui.theme.Sandrift

@Composable
fun GridScreen(product: List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp),
        modifier = Modifier.padding(bottom = 50.dp)
    ) {
        itemsIndexed(product) { _, per ->
            PersonsCard(product = per)
        }
    }
}

@Composable
fun PersonsCard(product: Product) {
    val count = remember { mutableIntStateOf(product.count) }
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .requiredHeight(200.dp)
            .shadow(elevation = 8.dp)
            .background(color = Color.White)
    ) {
        Column(modifier = Modifier.background(color = Color.White)) {
            Text(
                text = product.name,
                fontSize = 17.sp,
                color = DarkWood,
                modifier = Modifier
                    .padding(15.dp).align(Alignment.CenterHorizontally)
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly)
            {
                Text(
                    text = product.price.toString() + " руб.",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkWood
                )

                Text(
                    text = "-",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Sandrift,
                    modifier = Modifier.clickable( onClick = {
                        if (product.count > 0)
                        {
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
                    modifier = Modifier.clickable( onClick = {
                        count.intValue++
                        product.count++
                    })
                )
            }

                AsyncImage(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(product.imageUrl)
                        .crossfade(true)
                        .build(),
                    error = painterResource(id = R.drawable.baseline_coffee_24),
                    placeholder = painterResource(id = R.drawable.loading_img),
                    contentDescription = stringResource(id = R.string.contentDescription),
                    contentScale = ContentScale.Crop
                )
        }
    }
}