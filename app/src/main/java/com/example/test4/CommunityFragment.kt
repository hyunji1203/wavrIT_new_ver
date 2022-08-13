package com.example.test4

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import com.example.test4.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CommunityFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_community, container, false)

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2= view.findViewById<ViewPager2>(R.id.view_pager_2)

        val adapter= ViewPagerAdapter(activity?.supportFragmentManager!!,lifecycle)

        viewPager2.adapter=adapter

        //탭 레이아웃 이름 설정
        TabLayoutMediator(tabLayout,viewPager2){tab,position->
            when(position){
                0->{
                    tab.text="전체 커뮤니티"
                }
                1->{
                    tab.text="나의 커뮤니티"
                }
            }
        }.attach()

        return view
    }
}