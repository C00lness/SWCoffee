package com.example.domain.entities

data class Location(
    val id:Int,
    val name: String,
    val point: Point,
    var s: Int = 0
)
