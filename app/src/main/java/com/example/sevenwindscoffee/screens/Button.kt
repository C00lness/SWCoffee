package com.example.sevenwindscoffee.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sevenwindscoffee.ui.theme.AntiqueWhite
import com.example.sevenwindscoffee.ui.theme.BlackMagic

@Composable
fun Button (text: String, onClick: () -> Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .requiredHeight(55.dp)
        .clip(RoundedCornerShape(25.dp))
        .clickable { onClick() },
        colors = CardDefaults.cardColors(contentColor = AntiqueWhite, containerColor = BlackMagic))
    {
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center)
        {
            Text(text = text, fontSize = 18.sp)
        }
    }
}