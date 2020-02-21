package com.example.toysimulator.model

import android.util.Log

enum class Direction(val value: Int) {
    NORTH(0), EAST(1), SOUTH(2), WEST(3);

    fun rightDirection():Direction {
        return values()[(ordinal + 1) % values().size]
    }

    fun leftDirection():Direction {
        return if(value == 0) values()[values().size - 1] else values()[ordinal - 1]
    }

    companion object {
        fun getDirection(s: String): Direction {
            return when (s) {
                "NORTH" -> NORTH
                "SOUTH" -> SOUTH
                "EAST" -> EAST
                "WEST" -> WEST
                else -> NORTH
            }
        }
    }
}