package com.example.toysimulator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.toysimulator.model.Block
import com.example.toysimulator.model.Image
import kotlinx.android.synthetic.main.list_item.view.*

class GameViewAdapter : RecyclerView.Adapter<GameViewAdapter.ViewHolder>() {


    private var rowList:List<Block> = ArrayList();

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val ivImage:ImageView = view.iv_toy
        val tvTest:TextView = view.tv_test
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return rowList.size
    }

    fun updateList(rows: List<Block>) {
        rowList = rows
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var block = rowList[position]
        if(block.image == Image.NONE) holder.ivImage.visibility = View.INVISIBLE
        else {
            holder.ivImage.visibility = View.VISIBLE
            Glide.with(holder.itemView.context)
            .load(getVectorDrawable(block.image))
            .into(holder.ivImage)
        }
        holder.tvTest.text = block.index.toString()

    }

    private fun getVectorDrawable(s: Image): Int {
        return when (s) {
            Image.NORTH -> R.drawable.ic_north_black_24dp
            Image.EAST -> R.drawable.ic_east_black_24dp
            Image.SOUTH -> R.drawable.ic_south_black_24dp
            Image.WEST -> R.drawable.ic_west_black_24dp
            Image.NONE -> -1
        }
    }
}