package com.example.scuffedmap

import RoomEntity
import android.content.Context
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.scuffedmap.ui.theme.ScuffedMapTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(){
    val context = LocalContext.current
    var searchText by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf(mutableStateOf(mutableListOf<RoomEntity>())) }
    var searchTrigger by remember { mutableStateOf(0.toLong())}
    var searched by remember { mutableStateOf(true) }
    val state = rememberScrollState()

    LaunchedEffect(Unit) { state.animateScrollTo(0) }

    LaunchedEffect(searchTrigger) {
        performSearch(context, searchText, searchResults) { if (!searched){
                searched = true
                searchTrigger++
            }
        }
    }

    ScuffedMapTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Search") },
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Button(onClick = { searched = false; searchTrigger++}) {
                    Text("Search")
                }

                Column (
                    modifier = Modifier.verticalScroll(state)
                ){
                    searchResults.value.forEach { room ->
                        Text("Room Number: ${room.roomNum}, Description: ${room.description}, Teachers: ${room.teachers}")
                        Divider()
                    }
                }
            }
        }
    }
}

fun performSearch(context: Context, searchText: String, searchResults: MutableState<MutableList<RoomEntity>>, searchTrigger: ()->Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        val rooms = loadRoomsFromCsv(context)
        val filteredRooms = if (searchText.isNotEmpty()) {
            rooms.filter { room ->
                room.roomNum.contains(searchText, ignoreCase = true) ||
                        room.description.contains(searchText, ignoreCase = true) ||
                        room.teachers.contains(searchText, ignoreCase = true)
            }
        } else {
            mutableListOf()
        }

        withContext(Dispatchers.Main) {
            searchResults.value.clear()
            searchResults.value.addAll(filteredRooms)
            println("Search Results: ${searchResults.value}")
        }
        searchTrigger()
    }
}

fun loadRoomsFromCsv(context: Context): List<RoomEntity> {
    val rooms = mutableListOf<RoomEntity>()
    val inputStream = context.assets.open("rooms.csv")
    val bufferedReader = inputStream.bufferedReader()
    bufferedReader.useLines { lines ->
        lines.forEach { line ->
            val parts = line.split(",")
            val roomNum = parts[0]
            val teachers = parts[1]
            val description = parts[2]
            val room = RoomEntity(0, roomNum, teachers, description)
            rooms.add(room)
        }
    }
    return rooms
}

@Preview
@Composable
fun PreviewSearch() {
    Search()
}