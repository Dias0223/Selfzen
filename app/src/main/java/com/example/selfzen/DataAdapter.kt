package com.example.selfzen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter ( val dataset : Array<String>) : RecyclerView.Adapter<DataAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.cardTitle.text = dataset[position]

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        val cardTitle:TextView = itemView.findViewById(R.id.txt_proName)

    }

}