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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.scuffedmap.ui.theme.ScuffedMapTheme
import kotlin.math.cos
import kotlin.math.sin


// A surface container using the 'background' color from the theme
@Composable
fun MapImage() {
    var scale by remember {
        mutableStateOf(1f)
    }
    var offset by remember {
        mutableStateOf(Offset.Zero)
    }
    var rotation by remember {
        mutableStateOf(0f)
    }

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
            painter = painterResource(R.drawable.whsfloor1),
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


