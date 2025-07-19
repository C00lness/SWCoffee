package com.example.sevenwindscoffee.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.sevenwindscoffee.ui.theme.DarkWood

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(headerText: String, backPressed: (() -> Unit)? = null) {
    TopAppBar(title = {
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center)
        {
            val alpha = if(backPressed != null) 1f else 0f
            IconButton(onClick = { backPressed?.invoke() }, modifier = Modifier.align(Alignment.CenterStart).alpha(alpha)) {
                Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "")
            }
            Text(text = headerText, fontWeight = FontWeight.Bold, modifier = Modifier.align(
                Alignment.Center))
        } },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = DarkWood,
            navigationIconContentColor = DarkWood
        ))
}