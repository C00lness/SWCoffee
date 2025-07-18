package com.example.sevenwindscoffee.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.domain.entities.RequestUser
import com.example.sevenwindscoffee.presentation.ViewModel
import com.example.sevenwindscoffee.ui.theme.AntiqueWhite
import com.example.sevenwindscoffee.ui.theme.BlackMagic
import com.example.sevenwindscoffee.ui.theme.DarkWood
import com.example.sevenwindscoffee.ui.theme.Sandrift

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: ViewModel, onClick:() -> Unit)
{
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    var checked = remember { mutableStateOf(true) }

    TopAppBar(title = {
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center)
        {
            Text(if (checked.value) "Регистрация" else "Вход",
                fontWeight = FontWeight.Bold)
        }

    },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = DarkWood))

    Column(modifier = Modifier.padding(25.dp)) {
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center)
        {
            Column()
            {
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Switch(
                        checked = checked.value,
                        onCheckedChange = {checked.value = it},
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = AntiqueWhite,
                            checkedTrackColor = Sandrift,
                            uncheckedThumbColor = Sandrift,
                            uncheckedTrackColor = AntiqueWhite),
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }

                item("e-mail", email, "Example@example.ru")
                item("Пароль", password, "*******")
                if (checked.value) item("Подтвердите пароль", confirmPassword, "*******")
                Button(if (checked.value) "Регистрация" else "Вход", onClick = {
                    val user = RequestUser(email.value, password.value)
                    if (checked.value) viewModel.regLoginLocations(user)
                    else viewModel.loginLocations(user)
                    onClick()
                })
            }
        }
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
        modifier = Modifier.fillMaxWidth().padding(bottom = 25.dp)
    )
}