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
                fontSize = 14.sp,
                color = DarkWood,
                modifier = Modifier
                    .padding(5.dp)
            )
            Row(modifier = Modifier.padding(15.dp, 5.dp), horizontalArrangement = Arrangement.Absolute.SpaceBetween)
            {
                Text(
                    text = product.price.toString(),
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkWood
                )

                Text(
                    text = "-",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = AntiqueWhite,
                    modifier = Modifier.clickable( onClick = { Log.d("tempLog", "1")})
                )
                Text(
                    text = product.price.toString(),
                    fontSize = 19.sp,
                    color = DarkWood
                )
                Text(
                    text = "+",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = AntiqueWhite,
                    modifier = Modifier.clickable( onClick = {})
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
                    contentDescription = stringResource(id = R.string.content_description),
                    contentScale = ContentScale.Crop
                )
        }
    }
}