package com.example.sevenwindscoffee.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.domain.entities.RequestUser
import com.example.sevenwindscoffee.presentation.ViewModel
import com.example.sevenwindscoffee.ui.theme.AntiqueWhite
import com.example.sevenwindscoffee.ui.theme.BlackMagic
import com.example.sevenwindscoffee.ui.theme.Sandrift
import com.example.sevenwindscoffee.R

@Composable
fun HomeScreen(viewModel: ViewModel, onClick:() -> Unit)
{
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    var checked = remember { mutableStateOf(true) }

    TopBar(
        if (checked.value) stringResource(R.string.homeRegistartion)
        else stringResource(R.string.homeEnter), backPressed = null
    )
    Column(modifier = Modifier.fillMaxSize().padding(25.dp), verticalArrangement = Arrangement.SpaceEvenly )
    {
        Column()
        {
            Switch(
                checked = checked.value,
                onCheckedChange = { checked.value = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = AntiqueWhite,
                    checkedTrackColor = Sandrift,
                    uncheckedThumbColor = Sandrift,
                    uncheckedTrackColor = AntiqueWhite
                )
            )
            item(stringResource(R.string.homeEmailText), email, stringResource(R.string.homeEmailHintText))
            item(stringResource(R.string.homePasswordText), password, stringResource(R.string.homePasswordHintText))
            if (checked.value) item(stringResource(R.string.homePasswordRepeatText), confirmPassword, stringResource(R.string.homePasswordHintText))
        }

        Button(if (checked.value) stringResource(R.string.homeRegistartion) else stringResource(R.string.homeEnter), onClick = {
            val user = RequestUser(email.value, password.value)
            if (checked.value)
            {
                viewModel.regLoginLocations(user)
            }
            else viewModel.loginLocations(user)
            onClick()
        })
    }
}

@Composable
fun item (bottomText:String, editText: MutableState<String>, hintText:String)
{
    Text(text = bottomText, color = Sandrift, modifier = Modifier.padding(bottom = 10.dp))
    OutlinedTextField(
        value = editText.value,
        onValueChange = {editText.value = it},
        shape = RoundedCornerShape(30.dp),
        placeholder = {Text(text = hintText, color = Sandrift)},
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = Sandrift,
            focusedContainerColor = Sandrift,
            focusedTextColor = BlackMagic,
            focusedIndicatorColor = BlackMagic,
            unfocusedIndicatorColor = Sandrift
        ),
        modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp)
    )
}