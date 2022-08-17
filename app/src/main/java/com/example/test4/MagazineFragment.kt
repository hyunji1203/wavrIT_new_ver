package com.example.test4

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.example.test4.adapter.ViewPagerAdapter_magazine
import com.example.test4.magazine.*
import com.google.firebase.auth.FirebaseAuth

class MagazineFragment : Fragment() {

    lateinit var auth: FirebaseAuth

    lateinit var ourtown_btn : ImageView
    lateinit var job_btn : ImageView
    lateinit var health_btn : ImageView
    lateinit var activity_btn : ImageView
    lateinit var knowledge_btn : ImageView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_magazine, container, false)

        auth = FirebaseAuth.getInstance()

        ourtown_btn = view.findViewById(R.id.ourtown_btn)
        job_btn = view.findViewById(R.id.job_btn)
        health_btn = view.findViewById(R.id.health_btn)
        activity_btn = view.findViewById(R.id.activity_btn)
        knowledge_btn = view.findViewById(R.id.knowledge_btn)

        var id = view.findViewById<TextView>(R.id.mypage_id)

        var x = auth.currentUser?.email.toString()
        x += " 님의\n맞춤 매거진을 보여드릴께요"

        id.text = x

        // viewpager어댑터
        val viewPager_magazine = view.findViewById<ViewPager>(R.id.viewPager_magazine)
        // 슬라이드를 위한 어댑터 설정
        val pagerAdapter_middle = ViewPagerAdapter_magazine(childFragmentManager)
        viewPager_magazine.adapter = pagerAdapter_middle

        // 옆으로 넘어갈 때 이미지가 어떻게 보이는지 설정
        val dpValue: Int = 50
        val d: Float = getResources().getDisplayMetrics().density
        val margin: Int = dpValue * d.toInt()
        viewPager_magazine.setClipToPadding(false)
        viewPager_magazine.setPadding(margin, 0, margin, 0);
        viewPager_magazine.setPageMargin(30);

        ourtown_btn.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout, OurtownFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
        job_btn.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout, JobFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
        activity_btn.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            //transaction?.replace(R.id.frameLayout, ActivityFragment())
            transaction?.replace(R.id.frameLayout, ActivityFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
        health_btn.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout, HealthFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
        knowledge_btn.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout, KnowledgeFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        return view
    }
}