package com.example.sevenwindscoffee.screens

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sevenwindscoffee.R
import com.example.sevenwindscoffee.presentation.CoffeeUIState
import com.example.sevenwindscoffee.presentation.ViewModel
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

@Composable
fun MapScreen(viewModel: ViewModel, context: Context, onBackPressed: () -> Unit) {
    TopBar(stringResource(R.string.mapHeader), backPressed = {onBackPressed()})

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
            val startPosition = CameraPosition(startPoint, 12.5f, 0.0f, 0.0f)
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