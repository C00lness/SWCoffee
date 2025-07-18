package com.example.sevenwindscoffee.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sevenwindscoffee.presentation.ViewModel
import com.example.domain.entities.RequestUser

@Composable
fun homeScreen(viewModel: ViewModel)
{
    val user = RequestUser("Studio", "6667")
    viewModel.login(user)
    val state = viewModel.loginState.value
    Column(modifier = Modifier.padding(5.dp)) {
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center)
        {
            Text(text = state.toString())
        }
    }

}