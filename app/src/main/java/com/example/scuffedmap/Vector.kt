package com.example.scuffedmap

class Vector (var x: Double, var y: Double) {
    fun getDistanceBetween(other: Vector): Double {
        return Math.sqrt(Math.pow(other.x - x, 2.0) + Math.pow(other.y - y, 2.0));
    }
}