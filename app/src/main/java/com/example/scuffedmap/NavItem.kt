package com.example.scuffedmap

sealed class NavItem (
    val route: String,
    val icon: Int,
    val title: String
) {
    object Map : NavItem("map", R.drawable.ic_map, "Map")
    object Search : NavItem("search", R.drawable.ic_search, "Search")
    object Profile : NavItem("profile", R.drawable.ic_profile, "Profile"
}
