package com.example.test4.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.test4.R
import com.example.test4.community.category.*

class whole_community_Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_whole_community_, container, false)

        var c_body = view.findViewById<ImageView>(R.id.c_body)
        var c_mental = view.findViewById<ImageView>(R.id.c_mental)
        var job_btn = view.findViewById<ImageView>(R.id.job_btn)
        var health_btn = view.findViewById<ImageView>(R.id.health_btn)
        var activity_btn = view.findViewById<ImageView>(R.id.activity_btn)
        var knowledge_btn = view.findViewById<ImageView>(R.id.knowledge_btn)
        var soodo = view.findViewById<ImageView>(R.id.soodo)
        var ga_je = view.findViewById<ImageView>(R.id.ga_je)
        var mid = view.findViewById<ImageView>(R.id.mid)
        var south = view.findViewById<ImageView>(R.id.south)

        c_body.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, BodyFragment()).addToBackStack(null)
                .commit()
        }
        c_mental.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, MentalFragment()).addToBackStack(null)
                .commit()
        }
        job_btn.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, Community_ListFragment()).addToBackStack(null)
                .commit()
        }
        health_btn.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, C_HealthFragment()).addToBackStack(null)
                .commit()
        }
        knowledge_btn.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, C_KnowledgeFragment()).addToBackStack(null)
                .commit()
        }
        activity_btn.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, BabyFragment()).addToBackStack(null)
                .commit()
        }
        soodo.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, SeoulkFragment()).addToBackStack(null)
                .commit()
        }
        ga_je.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, JejuFragment()).addToBackStack(null)
                .commit()
        }
        mid.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, MiddleFragment()).addToBackStack(null)
                .commit()
        }
        south.setOnClickListener {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, SouthFragment()).addToBackStack(null)
                .commit()
        }


        return view
    }
}