package com.example.scuffedmap

open class Node (open var mCoordinates: Vector, open var floor: Byte, open var availableDestinations: Array<Node>)  {
    fun getDistBetween(other: Node): Double {
        return mCoordinates.getDistanceBetween(other.mCoordinates)
    }

    fun findShortestPath(destination: Node): Path {

        // TODO: Implement Dijkstra's algorithm to find the shortest path
        return Path(listOf(this, destination))
    }
}