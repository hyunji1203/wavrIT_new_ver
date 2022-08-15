package com.example.test4.search

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test4.R

class searchAdapter  (val search_List : ArrayList<search_data>) : RecyclerView.Adapter<searchAdapter.CustomViewHolder>() {
    //디데이가 우측 상단에 있는 리스트를 리사이클러뷰에 연결시켜주는 어댑터

    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): searchAdapter.CustomViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.search_history_item, parent, false)

        return CustomViewHolder(view).apply {

            //아이템 클릭 시 해당 장학금의 이름이 상세설명 창으로 넘어가도록 intent이용하는 코드 작성
            itemView.setOnClickListener {
                val curPos : Int = adapterPosition
                val word : search_data = search_List.get(curPos)


            }
        }
    }

    override fun onBindViewHolder(holder: searchAdapter.CustomViewHolder, position: Int) {
        holder.word.text = search_List.get(position).word
        holder.count.text = search_List.get(position).count.toString()
    }


    class CustomViewHolder (itemVIew : View) : RecyclerView.ViewHolder(itemVIew) {

        val word = itemVIew.findViewById<TextView>(R.id.word)
        val count = itemVIew.findViewById<TextView>(R.id.count)
    }

    override fun getItemCount(): Int {
        return search_List.size
    }
}