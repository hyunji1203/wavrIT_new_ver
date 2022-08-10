package com.example.test4

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore

class M_Adapter (private val context: jobFragment): RecyclerView.Adapter<M_Adapter.ViewHolder>() {

    private var jobList = mutableListOf<Data>()

    fun setListData(data:MutableList<Data>){
        jobList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): M_Adapter.ViewHolder {

        var view1 : View?
        var view2 : View?

        return when(viewType){
            1 -> {
                view2 = LayoutInflater.from(parent.context).inflate(
                    R.layout.item,
                    parent,
                    false
                )
                ViewHolder(view2).apply {
                    itemView.setOnClickListener {

                    }
                }
            }
            else -> {
                view1 = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_r,
                    parent,
                    false
                )
                ViewHolder(view1).apply {
                    itemView.setOnClickListener {

                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: M_Adapter.ViewHolder, position: Int) {
        val job : Data = jobList[position]

        //holder.title_ex.text = job.title
        //holder.content_ex.text = job.content
        holder.summary.text = job.summary
        //holder.imageurl.text = job.imageurl

        Glide.with(holder.itemView)
            .load(job.imageurl)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        //val title_ex : TextView = itemView.findViewById(R.id.title_ex)
        //val content_ex : TextView = itemView.findViewById(R.id.content_ex)
        val summary : TextView = itemView.findViewById(R.id.summary)
        //val imageurl : TextView = itemView.findViewById(R.id.imageurl_ex)
        val image : ImageView = itemView.findViewById(R.id.image)
    }
    override fun getItemViewType(position: Int): Int {
        return jobList[position].type
    }

}