package com.example.toysimulator

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.toysimulator.model.Direction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var tvReport:TextView
    lateinit var adapter: GameViewAdapter
    lateinit var viewModel: MainViewModel
    lateinit var spXAxis:Spinner
    lateinit var spYAxix:Spinner
    lateinit var spDirection:Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var numbers = arrayOf(0,1,2,3,4)
        var directionArray = arrayOf("NORTH","EAST","SOUTH","WEST")
        tvReport = findViewById(R.id.tv_report)
        spXAxis = findViewById(R.id.sp_xAxis)
        spYAxix = findViewById(R.id.sp_yAxis)
        spDirection = findViewById(R.id.sp_direction)
        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, numbers)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spXAxis.adapter = arrayAdapter
        spYAxix.adapter  = arrayAdapter

        spXAxis.setSelection(0)
        spYAxix.setSelection(0)

        var arrayAdapterDirection = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, directionArray)
        arrayAdapterDirection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spDirection.adapter = arrayAdapterDirection
        spDirection.setSelection(0)

        rv_positions.layoutManager = GridLayoutManager(this, 5)

        // Access the RecyclerView Adapter and load the data into it
        adapter = GameViewAdapter()
        rv_positions.adapter = adapter

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.listBlock.observe(this, Observer {
            adapter.updateList(it)
        })

    }

    fun placeToy(x:Int, y:Int, direction: Direction) {
        viewModel.isToyPlaced = viewModel.game.placeToy(x, y, direction)
    }

    fun rightClick(view: View) {
        if(viewModel.isToyPlaced) viewModel.game.toyRight()
        else Toast.makeText(this, "Please place the toy first",Toast.LENGTH_SHORT)
        tvReport.visibility = View.GONE
        //tvReport.text = viewModel.game.report()
    }
    fun move(view: View) {
        if(viewModel.isToyPlaced) viewModel.game.moveToy()
        else Toast.makeText(this, "Please place the toy first",Toast.LENGTH_SHORT)
        tvReport.visibility = View.GONE
        //tvReport.text = viewModel.game.report()
    }
    fun leftClick(view: View) {
        if(viewModel.isToyPlaced) viewModel.game.toyLeft()
        else Toast.makeText(this, "Please place the toy first",Toast.LENGTH_SHORT)
        tvReport.visibility = View.GONE
        //tvReport.text = viewModel.game.report()
    }

    fun placeClick(view: View) {
        placeToy(Integer.parseInt(spXAxis.selectedItem.toString()),Integer.parseInt(spYAxix.selectedItem.toString())
            , Direction.getDirection(spDirection.selectedItem.toString()))
        tvReport.visibility = View.GONE
    }

    fun reportClick(view: View) {
        val report:String = viewModel.game.report()
        tvReport.visibility = View.VISIBLE
        tvReport.text = report
    }

}
