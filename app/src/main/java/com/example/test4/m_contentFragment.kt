package com.example.test4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Dimension
import com.bumptech.glide.Glide

class m_contentFragment : Fragment(), onBackPressedListener {

    lateinit var c_content : TextView
    lateinit var c_title : TextView
    lateinit var c_image : ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_m_content, container, false)

        c_title = view.findViewById(R.id.c_title)
        c_content = view.findViewById(R.id.c_content)
        c_image = view.findViewById<ImageView>(R.id.c_image)

        val content = arguments?.getString("content")
        val title = arguments?.getString("title")
        val imageurl = arguments?.getString("imageurl")

        c_content.text = content.toString()
        c_title.text = title.toString()

        //c_content.setTextSize(Dimension.SP, 20F)

        Glide.with(this)
            .load(imageurl)
            .into(c_image)

        return view
    }

    override fun onBackPressed() {
        /*if (this is HomeFragment){
            activity?.finish()
        }*/

        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        fragmentManager?.popBackStack()
    }

}