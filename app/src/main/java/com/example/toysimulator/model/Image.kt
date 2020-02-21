package com.example.toysimulator.model

enum class Image(val value: Int) {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3),
    NONE(5);
}
data class Block(var index:Int, var image: Image)