package com.example.test4.community

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
import com.example.test4.adapter.Ma_Adapter
import com.example.test4.m_contentFragment

class Cl_Adapter (private val context: Fragment): RecyclerView.Adapter<Cl_Adapter.ViewHolder>() {

    private var cList = mutableListOf<data_cl>()

    fun setListData(data: MutableList<data_cl>) {
        cList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view1: View?
        var view2: View?

        return when (viewType) {
            1 -> {
                view2 = LayoutInflater.from(parent.context).inflate(R.layout.cl_p_item, parent, false)
                ViewHolder(view2).apply {
                    itemView.setOnClickListener {
                        /*var mContentfragment = m_contentFragment()
                        var bundle = Bundle()
                        bundle.putString("title", title_ex.text.toString())
                        bundle.putString("content", content_ex.text.toString())
                        bundle.putString("imageurl", imageurl.text.toString())
                        mContentfragment.arguments =
                            bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

                        context.activity?.supportFragmentManager!!.beginTransaction()
                            .replace(R.id.frameLayout, mContentfragment)
                            .addToBackStack(null)?.commit()*/
                    }
                }
            }
            else -> {
                view1 = LayoutInflater.from(parent.context).inflate(R.layout.cl_item, parent, false)
                ViewHolder(view1).apply {
                    itemView.setOnClickListener {

                        /*var mContentfragment = m_contentFragment()
                        var bundle = Bundle()
                        bundle.putString("title", title_ex.text.toString())
                        bundle.putString("content", content_ex.text.toString())
                        bundle.putString("imageurl", imageurl.text.toString())
                        mContentfragment.arguments =
                            bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

                        context.activity?.supportFragmentManager!!.beginTransaction()
                            .replace(R.id.frameLayout, mContentfragment).addToBackStack(null)
                            .commit()*/
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val c : data_cl = cList[position]

        holder.title.text = c.title
        holder.con.text = c.content
        holder.com.text = c.comment.toString()
        holder.date.text = c.date
        holder.nick.text = c.nickname
        holder.scr.text = c.scrap.toString()

        Glide.with(holder.itemView)
            .load(c.image)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return cList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.cl_title)
        val nick: TextView = itemView.findViewById(R.id.cl_nickname)
        val con: TextView = itemView.findViewById(R.id.cl_content)
        val date: TextView = itemView.findViewById(R.id.date)
        val image : ImageView = itemView.findViewById(R.id.cl_image)
        val scr: TextView = itemView.findViewById(R.id.scrap)
        val com: TextView = itemView.findViewById(R.id.comment)
    }

    override fun getItemViewType(position: Int): Int {
        return cList[position].type
    }

}