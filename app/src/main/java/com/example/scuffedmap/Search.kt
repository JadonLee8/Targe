package com.example.scuffedmap

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.scuffedmap.ui.theme.ScuffedMapTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(){
    ScuffedMapTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            var searchText by remember { mutableStateOf("") }

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Search") }
            )

            Button(onClick = { performSearch(searchText) }) {
                Text("Search")
            }
        }
    }
}

fun performSearch(searchText: String) {

}

@Preview
@Composable
fun PreviewSearch() {
    Search()
}
