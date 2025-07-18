package com.example.sevenwindscoffee.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sevenwindscoffee.presentation.ViewModel
import com.example.domain.entities.RequestUser
import com.example.sevenwindscoffee.ui.theme.AntiqueWhite
import com.example.sevenwindscoffee.ui.theme.BlackMagic
import com.example.sevenwindscoffee.ui.theme.DarkWood
import com.example.sevenwindscoffee.ui.theme.Sandrift
import com.example.sevenwindscoffee.ui.theme.Silver

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen(viewModel: ViewModel)
{
    val user = RequestUser("Studio", "6667")
    viewModel.login(user)
    val state = viewModel.loginState.value

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
            titleContentColor = DarkWood,
            navigationIconContentColor = Color.LightGray,
            actionIconContentColor = Color.LightGray))

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

                Card(modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(55.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .clickable { },
                    colors = CardDefaults.cardColors(contentColor = AntiqueWhite, containerColor = BlackMagic))
                {
                    Box(modifier = Modifier.fillMaxSize(), Alignment.Center)
                    {
                        Text(text = if (checked.value) "Регистрация" else "Вход", fontSize = 18.sp)
                    }
                }
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