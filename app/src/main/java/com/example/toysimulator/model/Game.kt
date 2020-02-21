package com.example.toysimulator.model

import android.util.Log
import androidx.lifecycle.MutableLiveData

class Game(private val maxRows: Int, private val maxColums: Int) {

    var list = mutableListOf<Block>()
    var liveList = MutableLiveData<List<Block>>()

    init {
        initialise()
    }

    private fun initialise() {
        for (r in 0 until maxRows) {
            for (c in 0 until maxColums) {
                list.add(Block((r * maxColums) + c, Image.NONE))
            }
        }
        liveList.value = list
    }

    private fun clear() {
        list.find { it.image != Image.NONE }?.image = Image.NONE
    }

    private fun update(position: Position) {
        clear()
        list.find { it.index == position.xAxis * maxColums + position.yAxis }?.image = Image.valueOf(position.direction.toString())
        liveList.value = list
    }

    var toy: Toy = Toy(maxRows, maxColums)

    fun placeToy(xAxis: Int, yAxis: Int, direction: Direction): Boolean {
        //return toy.place(xAxis, yAxis, direction)
        var isSuccess =  toy.place(xAxis, yAxis, direction)
        if(isSuccess) update(toy.position)
        return isSuccess
    }

    fun moveToy(): Boolean {
        var isSuccess =  toy.move()
        if(isSuccess) update(toy.position)
        return isSuccess
    }

    fun toyLeft() {
        toy.leftTurn()
        update(toy.position)
    }

    fun toyRight() {
        toy.rightTurn()
        update(toy.position)
    }

    fun report() = toy.report()

}