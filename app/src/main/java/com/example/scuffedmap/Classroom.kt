package com.example.scuffedmap

class Classroom(var teachers: Array<String>, var computerLab: Boolean, var courses: Array<String>,
                roomNum: String, description: String, hasWindows: Boolean, mCoordinates: Vector,
                floor: Byte, availableDestinations: Array<Node>
) : Room(roomNum, description, hasWindows, mCoordinates, floor, availableDestinations)  {
}