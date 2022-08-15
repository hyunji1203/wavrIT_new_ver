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

            itemView.setOnClickListener {
                val curPos : Int = adapterPosition
                val s_data : search_data = search_List.get(curPos)

                var word = s_data.word

                dbManager = DBManager(view.context, "search_history", null, 1)
                sqlitedb = dbManager.writableDatabase

                //눌러진 아이템의 이름을 받아와 데이터 베이스에서 그 이름을 삭제하고 알람을 해제함
                sqlitedb.execSQL("DELETE FROM search_history WHERE word = '"+ word +"';")

                sqlitedb.close()
                dbManager.close()

                var intent = Intent(view.context, SearchActivity::class.java)
                view.context.startActivity(intent)
            }
        }
    }

    override fun onBindViewHolder(holder: searchAdapter.CustomViewHolder, position: Int) {
        holder.s_history.text = search_List.get(position).word
        holder.count.text = search_List.get(position).count.toString()
    }


    class CustomViewHolder (itemVIew : View) : RecyclerView.ViewHolder(itemVIew) {

        val s_history = itemVIew.findViewById<TextView>(R.id.s_history)
        val count = itemVIew.findViewById<TextView>(R.id.count)
    }

    override fun getItemCount(): Int {
        return search_List.size
    }
}