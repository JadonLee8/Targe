package com.example.scuffedmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.scuffedmap.ui.theme.ScuffedMapTheme
import kotlin.math.cos
import kotlin.math.sin


// A surface container using the 'background' color from the theme
@Composable
fun MapImage() {
    var scale by remember {
        mutableFloatStateOf(1f)
    }
    var offset by remember {
        mutableStateOf(Offset.Zero)
    }
    var rotation by remember {
        mutableFloatStateOf(0f)
    }
    var isSwitchChecked by remember { mutableStateOf(false) }
    var currentPainter by remember { mutableStateOf(R.drawable.whsfloor1) }



    val layerToggleColors = SwitchDefaults.colors(
        checkedThumbColor = MaterialTheme.colorScheme.primary,
        uncheckedThumbColor = MaterialTheme.colorScheme.primary,
        checkedTrackColor = MaterialTheme.colorScheme.primary,
        uncheckedTrackColor = MaterialTheme.colorScheme.primary,
        checkedBorderColor = MaterialTheme.colorScheme.primary,
        uncheckedBorderColor = MaterialTheme.colorScheme.primary,
        checkedIconColor = MaterialTheme.colorScheme.onPrimary,
        uncheckedIconColor = MaterialTheme.colorScheme.onPrimary,
        disabledCheckedBorderColor = MaterialTheme.colorScheme.primary,
        disabledCheckedIconColor = MaterialTheme.colorScheme.onPrimary,
        disabledCheckedTrackColor = MaterialTheme.colorScheme.primary,
        disabledUncheckedBorderColor = MaterialTheme.colorScheme.primary,
        disabledUncheckedIconColor = MaterialTheme.colorScheme.onPrimary,
        disabledUncheckedTrackColor = MaterialTheme.colorScheme.primary,
        disabledCheckedThumbColor = MaterialTheme.colorScheme.primary,
        disabledUncheckedThumbColor = MaterialTheme.colorScheme.primary
    )


    Switch(
        checked = isSwitchChecked,
        onCheckedChange = { isChedked ->
            isSwitchChecked = isChedked
            if (isSwitchChecked) {
                currentPainter = R.drawable.whsfloor2
            } else {
                currentPainter = R.drawable.whsfloor1
            }
        },
        enabled = true,

        colors = layerToggleColors,
        modifier = Modifier
            .rotate(-90f)
            .zIndex(5f)
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        val state =
            rememberTransformableState { zoomChange, panChange, rotationChange ->
                scale = (scale * zoomChange).coerceIn(1f, 5f)

                val extraWidth = (scale - 1) * constraints.maxWidth
                val extraHeight = (scale - 1) * constraints.maxHeight

                val maxX = extraWidth / 2
                val maxY = extraHeight / 2
                rotation = (rotation + rotationChange) % 360
                val radian = Math.toRadians(rotation.toDouble())

                val cos = cos(radian).toFloat()
                val sin = sin(radian).toFloat()

                // Apply the rotation matrix to the pan change
                val rotatedPanChange = Offset(
                    x = panChange.x * cos - panChange.y * sin,
                    y = panChange.x * sin + panChange.y * cos
                )

                offset = Offset(
                    x = ((offset.x + scale * rotatedPanChange.x)).coerceIn(-maxX, maxX),
                    y = ((offset.y + scale * rotatedPanChange.y)).coerceIn(-maxY, maxY),
                )
            }
        Image(
            painter = painterResource(currentPainter),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                    rotationZ = rotation
                }
                .transformable(state)
        )
    }
}


