package com.example.test4

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.example.test4.adapter.ViewPagerAdapter_home
import com.example.test4.adapter.ViewPagerAdapter_home_recommend
import com.example.test4.adapter.ViewPagerAdapter_magazine
import com.example.test4.home.recommend.AddFragment
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    lateinit var auth: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_home, container, false)

        var add1 = view.findViewById<TextView>(R.id.add1)
        var add2 = view.findViewById<ImageView>(R.id.add2)

        var home_id = view.findViewById<TextView>(R.id.home_id)

        auth = FirebaseAuth.getInstance()

        home_id.text = auth.currentUser?.email

        // viewpager 어댑터1
        val viewPager_home = view.findViewById<ViewPager>(R.id.viewPager_magazine)
        // 슬라이드를 위한 어댑터 설정
        val pagerAdapter_home = ViewPagerAdapter_home(childFragmentManager)
        viewPager_home.adapter = pagerAdapter_home

        // viewpager 어댑터2
        val viewPager_home_recommend = view.findViewById<ViewPager>(R.id.viewPager_home)
        // 슬라이드를 위한 어댑터 설정
        val pagerAdapter_recommend = ViewPagerAdapter_home_recommend(childFragmentManager)
        viewPager_home_recommend.adapter = pagerAdapter_recommend


        // 배너 옆으로 넘어갈 때 이미지가 어떻게 보이는지 설정
        val dpValue1: Int = 0
        val d1: Float = resources.displayMetrics.density
        val margin1: Int = dpValue1 * d1.toInt()
        viewPager_home.clipToPadding = false
        viewPager_home.setPadding(margin1, 0, margin1, 0);
        viewPager_home.pageMargin = 30


        // 추천 정보 옆으로 넘어갈 때 이미지가 어떻게 보이는지 설정
        val dpValue2: Int = 200
        val d2: Float = resources.displayMetrics.density
        val margin2: Int = dpValue2 * d2.toInt()
        viewPager_home_recommend.clipToPadding = false
        viewPager_home_recommend.setPadding(0, 0, margin2, 0);
        viewPager_home_recommend.pageMargin = 50

        add1.setOnClickListener {
            var AddFragment = AddFragment()

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, AddFragment).addToBackStack(null)
                .commit()
        }
        add2.setOnClickListener {
            var AddFragment = AddFragment()

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, AddFragment).addToBackStack(null)
                .commit()
        }

        return view
    }

}