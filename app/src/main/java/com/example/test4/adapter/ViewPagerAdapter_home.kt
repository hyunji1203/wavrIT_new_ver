package com.example.test4.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.test4.Magazine_firstFragment
import com.example.test4.Magazine_secondFragment
import com.example.test4.Magazine_thirdFragment
import com.example.test4.home.Home_firstFragment
import com.example.test4.home.Home_secondFragment
import com.example.test4.home.Home_thirdFragment

class ViewPagerAdapter_home (fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when(position) {

            0       ->  Home_firstFragment()

            1       ->  Home_secondFragment()

            else       -> Home_thirdFragment()

        }

    }

    // 생성 할 Fragment 의 개수
    override fun getCount() = 3

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

}