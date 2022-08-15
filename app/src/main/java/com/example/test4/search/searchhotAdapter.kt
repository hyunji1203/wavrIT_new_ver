package com.example.test4.search

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test4.R

class searchhotAdapter  (val search_List : ArrayList<search_data>) : RecyclerView.Adapter<searchhotAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): searchhotAdapter.CustomViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.search_hot_item, parent, false)

        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {
                val curPos : Int = adapterPosition
                val s_data : search_data = search_List.get(curPos)
            }
        }
    }

    override fun onBindViewHolder(holder: searchhotAdapter.CustomViewHolder, position: Int) {
        holder.s_h_text.text = search_List.get(position).word
        holder.s_h_count.text = search_List.get(position).count.toString()
    }


    class CustomViewHolder (itemVIew : View) : RecyclerView.ViewHolder(itemVIew) {
        val s_h_text = itemVIew.findViewById<TextView>(R.id.s_h_text)
        val s_h_count = itemVIew.findViewById<TextView>(R.id.s_h_count)
    }

    override fun getItemCount(): Int {
        return search_List.size
    }
}