package com.example.test4.adapter

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
import com.example.test4.R
import com.example.test4.m_contentFragment

class Mh_Adapter (private val context: Fragment): RecyclerView.Adapter<Mh_Adapter.ViewHolder>() {

    private var jobList = mutableListOf<Data>()

    fun setListData(data: MutableList<Data>) {
        jobList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view1: View?
        var view2: View?

        return when (viewType) {
            1 -> {
                view2 = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
                ViewHolder(view2).apply {
                    itemView.setOnClickListener {
                        var mContentfragment = m_contentFragment()
                        var bundle = Bundle()
                        bundle.putString("title", title_ex.text.toString())
                        bundle.putString("content", content_ex.text.toString())
                        bundle.putString("imageurl", imageurl.text.toString())
                        mContentfragment.arguments =
                            bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

                        context.activity?.supportFragmentManager!!.beginTransaction()
                            .replace(R.id.frameLayout, mContentfragment).addToBackStack(null)
                            .commit()
                    }
                }
            }
            else -> {
                view1 = LayoutInflater.from(parent.context).inflate(R.layout.item_r, parent, false)
                ViewHolder(view1).apply {
                    itemView.setOnClickListener {

                        var mContentfragment = m_contentFragment()
                        var bundle = Bundle()
                        bundle.putString("title", title_ex.text.toString())
                        bundle.putString("content", content_ex.text.toString())
                        bundle.putString("imageurl", imageurl.text.toString())
                        mContentfragment.arguments =
                            bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

                        context.activity?.supportFragmentManager!!.beginTransaction()
                            .replace(R.id.frameLayout, mContentfragment).addToBackStack(null)
                            .commit()
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
        return jobList[position].type
    }
}