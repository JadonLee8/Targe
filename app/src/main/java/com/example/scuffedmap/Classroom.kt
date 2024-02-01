package com.example.scuffedmap

class Classroom(var teachers: Array<String>,
                     var computerLab: Boolean,
                     var courses: Array<String>,
                     override var roomNum: String,
                     override var description: String,
                     override var hasWindows: Boolean,
                     override var mCoordinates: Vector,
                     override var floor: Byte,
                     override var availableDestinations: Array<Node>
) : Room(roomNum, description, hasWindows, mCoordinates, floor, availableDestinations)  {
}