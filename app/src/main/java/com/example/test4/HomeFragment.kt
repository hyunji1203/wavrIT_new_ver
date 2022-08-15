package com.example.test4

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.example.test4.adapter.ViewPagerAdapter_home
import com.example.test4.adapter.ViewPagerAdapter_magazine


class HomeFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_home, container, false)

        // viewpager 어댑터
        val viewPager_home = view.findViewById<ViewPager>(R.id.viewPager_magazine)
        // 슬라이드를 위한 어댑터 설정
        val pagerAdapter_middle = ViewPagerAdapter_home(childFragmentManager)
        viewPager_home.adapter = pagerAdapter_middle

        // 옆으로 넘어갈 때 이미지가 어떻게 보이는지 설정
        val dpValue: Int = 0
        val d: Float = getResources().getDisplayMetrics().density
        val margin: Int = dpValue * d.toInt()
        viewPager_home.setClipToPadding(false)
        viewPager_home.setPadding(margin, 0, margin, 0);
        viewPager_home.setPageMargin(30)

        return view
    }

}