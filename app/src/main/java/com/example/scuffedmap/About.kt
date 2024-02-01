package com.example.scuffedmap

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scuffedmap.ui.theme.ScuffedMapTheme

@Composable
fun About() {
    ScuffedMapTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Text(
                "Parge",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                "Parge is the interactive indoors map." +
                        "With 1000s of nodes, it enables navigation" +
                        "through large buildings.",
                modifier = Modifier
                    .padding(top = 40.dp)
                    .padding(horizontal = 10.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewAbout() {
    About()
}