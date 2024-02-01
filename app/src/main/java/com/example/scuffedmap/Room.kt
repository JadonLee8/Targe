package com.example.scuffedmap

open class Room (
    open var roomNum: String,
    open var description: String,
    open var hasWindows: Boolean,
    override var mCoordinates: Vector,
    override var floor: Byte,
    override var availableDestinations: Array<Node>
) : Node(mCoordinates, floor, availableDestinations) {
}