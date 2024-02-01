package com.example.scuffedmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.scuffedmap.ui.theme.ScuffedMapTheme
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.navigation.compose.rememberNavController
import com.example.scuffedmap.ui.theme.UTOrange

@Composable
fun bottomNav(navController: NavController){
    ScuffedMapTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
        ) {
            BottomNavigation (
                backgroundColor = UTOrange
            ){
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                    selected = false,
                    onClick = { navController.navigate("map") }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Search, contentDescription = null) },
                    selected = false,
                    onClick = { navController.navigate("search") }
                )
                BottomNavigationItem(
                    selected = false,
                    onClick = { navController.navigate("about") },
                    icon = { Icon(Icons.Filled.Info, contentDescription = null) })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomNav() {
    ScuffedMapTheme {
        val navController = rememberNavController()
        bottomNav(navController)
    }
}
