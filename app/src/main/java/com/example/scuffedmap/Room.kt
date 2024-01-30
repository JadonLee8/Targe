package com.example.scuffedmap

open class Room (var roomNum: String, var description: String, var hasWindows: Boolean,
            mCoordinates: Vector, floor: Byte, availableDestinations: Array<Node>
) : Node(mCoordinates, floor, availableDestinations) {
}