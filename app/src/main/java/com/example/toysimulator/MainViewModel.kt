package com.example.toysimulator

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.toysimulator.model.Block
import com.example.toysimulator.model.Game

class MainViewModel : ViewModel() {
    var isToyPlaced: Boolean = false
    private val TAG = MainViewModel::class.qualifiedName
    var game = Game(5, 5)
    var listBlock:LiveData<List<Block>> = game.liveList
}