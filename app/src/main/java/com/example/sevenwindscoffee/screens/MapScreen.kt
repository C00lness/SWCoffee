package com.example.sevenwindscoffee.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sevenwindscoffee.R
import com.example.sevenwindscoffee.presentation.CoffeeUIState
import com.example.sevenwindscoffee.presentation.ViewModel
import com.example.sevenwindscoffee.ui.theme.DarkWood
import ru.sulgik.mapkit.compose.Placemark
import ru.sulgik.mapkit.compose.YandexMap
import ru.sulgik.mapkit.compose.bindToLifecycleOwner
import ru.sulgik.mapkit.compose.rememberAndInitializeMapKit
import ru.sulgik.mapkit.compose.rememberCameraPositionState
import ru.sulgik.mapkit.compose.rememberPlacemarkState
import ru.sulgik.mapkit.geometry.Point
import ru.sulgik.mapkit.map.CameraPosition
import ru.sulgik.mapkit.map.ImageProvider
import ru.sulgik.mapkit.map.fromResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(viewModel: ViewModel, context: Context, onBackPressed: () -> Unit) {
    TopAppBar(title = {
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center)
        {
            Text(text = "Карта", fontWeight = FontWeight.Bold)
        }
    },
        navigationIcon={ IconButton({onBackPressed() }) { Icon(
            Icons.Filled.KeyboardArrowLeft,
            contentDescription = "Назад")
        }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = DarkWood,
            navigationIconContentColor = DarkWood
        ))

    val viewState = viewModel.locationsState.collectAsStateWithLifecycle()
    when (val state = viewState.value)
    {
        is CoffeeUIState.Loading -> LoadingScreen()
        is CoffeeUIState.SuccessLocations ->
        {
            val points: MutableList<Point> = mutableListOf()
            state.locations.forEach {
                points.add(Point(it.point.latitude.toDouble(), it.point.longitude.toDouble()))
            }
            val startPoint = points[0]
            val startPosition = CameraPosition(startPoint, 16.5f, 0.0f, 0.0f)
            rememberAndInitializeMapKit().bindToLifecycleOwner()
            val cameraPositionState = rememberCameraPositionState { position = startPosition }
            YandexMap(
                cameraPositionState = cameraPositionState,
                modifier = Modifier.fillMaxSize().padding(top = 100.dp)
            )
            {
                val imageProvider = ImageProvider.fromResource(context, R.drawable.yandex)
                points.forEach {
                    Placemark(
                        state = rememberPlacemarkState(it),
                        icon = imageProvider,
                        visible = true,
                        zIndex = 1.0f,
                        opacity = 1.0f
                    )
                }
            }
        }
        is CoffeeUIState.Error -> ErrorScreen(state.message)
        else -> {}
    }
}