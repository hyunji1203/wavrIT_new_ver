package com.example.test4.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test4.Data
import com.example.test4.MainActivity
import com.example.test4.R
import com.example.test4.m_contentFragment

class S_Adapter (private val context: Activity, searchword : String): RecyclerView.Adapter<S_Adapter.ViewHolder>() {

    private var jobList = mutableListOf<Data>()

    fun setListData(data: MutableList<Data>) {
        jobList = data
    }

    val searchword = searchword

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view1: View?
        val view2: View?

        return when (viewType) {
            1 -> {
                view2 = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
                ViewHolder(view2).apply {
                    itemView.setOnClickListener {

//                        var myExam = Exam(title_ex.text.toString(), content_ex.text.toString(), imageurl.text.toString(), 1)
//
//                        var intent = Intent(view2.context, m_contentFragment::class.java)
//                        intent.putExtra("examKey", myExam)
//                        view2.context.startActivity(intent)

                    }
                }
            }
            0 -> {
                view1 = LayoutInflater.from(context).inflate(R.layout.item_r, parent, false)
                ViewHolder(view1).apply {
                    itemView.setOnClickListener {

//                        var myExam = Exam(title_ex.text.toString(), content_ex.text.toString(), imageurl.text.toString(), 1)
//
//                        var intent = Intent(view1.context, m_contentFragment::class.java)
//                        intent.putExtra("examKey", myExam)
//                        view1.context.startActivity(intent)
                    }
                }
            }
            else -> {
                view1 = LayoutInflater.from(context).inflate(R.layout.item_none, parent, false)
                ViewHolder(view1).apply {
                    itemView.setOnClickListener {

                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val job: Data = jobList[position]

        holder.title_ex.text = job.title
        holder.content_ex.text = job.content
        holder.summary.text = job.summary
        holder.imageurl.text = job.imageurl

        Glide.with(holder.itemView)
            .load(job.imageurl)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title_ex: TextView = itemView.findViewById(R.id.title_ex)
        val content_ex: TextView = itemView.findViewById(R.id.content_ex)
        val summary: TextView = itemView.findViewById(R.id.summary)
        val imageurl: TextView = itemView.findViewById(R.id.imageurl_ex)
        val image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun getItemViewType(position: Int): Int {
        if (jobList[position].title.contains(searchword)){
            if (jobList[position].type == 1){
                return 1
            }
            else{
                return 0
            }
        }
        else{
            return 3
        }
    }
}