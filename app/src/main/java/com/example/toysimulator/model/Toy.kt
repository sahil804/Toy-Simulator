package com.example.toysimulator.model

class Toy() {

    var isPlaced: Boolean = false
    lateinit var position: Position

    constructor(maxRows: Int, maxColumns:Int) : this() {
        position = Position(maxRows, maxColumns)
    }
    fun place(xAxis:Int, yAxis:Int, direction: Direction):Boolean {
        position.xAxis = xAxis
        position.yAxis = yAxis
        position.direction = direction
        if(position.isPositionValid()) isPlaced = true
        return isPlaced
    }

    fun move():Boolean {
        return position.updatePosition()
    }

    fun leftTurn() = position.leftTurn()

    fun rightTurn() = position.rightTurn()

    fun report():String {
        return "Toy is at ${position.xAxis}, ${position.yAxis} facing ${position.direction.name}"
    }
}