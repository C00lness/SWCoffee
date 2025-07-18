package com.example.sevenwindscoffee.presentation

interface Route {
    val route: String
}

object HomePage : Route {
    override val route: String = "home_page"
}

object Locations : Route {
    override val route: String = "locations"
}

object Products : Route {
    override val route: String = "products"
}

object Map : Route {
    override val route: String = "map"
}