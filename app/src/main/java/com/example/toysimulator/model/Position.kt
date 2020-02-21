package com.example.toysimulator.model

class Position(private val rows: Int, private val columns: Int) {

    var xAxis = 0
    var yAxis = 0
    var direction = Direction.NORTH

    fun updatePosition(): Boolean {
        return when (direction) {
            Direction.SOUTH -> {
                if (xAxis + 1 >= columns) false else {
                    xAxis += 1
                    true
                }
            }
            Direction.EAST -> {
                if (yAxis + 1 >= columns ) false else {
                    yAxis += 1
                    true
                }
            }
            Direction.NORTH -> {
                if (xAxis - 1 < 0) false else {
                    xAxis -= 1
                    true
                }
            }
            Direction.WEST -> {
                if (yAxis - 1 < 0) false else {
                    yAxis -= 1
                    true
                }
            }
        }
    }

    fun isPositionValid():Boolean {
        return xAxis in 0 until rows && yAxis in 0 until columns
    }

    fun leftTurn() {
        direction = direction.leftDirection()
    }

    fun rightTurn() {
       direction = direction.rightDirection()
    }

}