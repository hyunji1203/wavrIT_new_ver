package com.example.test4.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.test4.home.recommend.*

class ViewPagerAdapter_home_recommend (fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when(position) {

            0       ->  Recommend_firstFragment()

            1       ->  Recommend_secondFragment()

            2       ->  Recommend_thirdFragment()

            3       ->  Recommend_fourthFragment()

            else       -> Recommend_fifthFragment()

        }

    }

    // 생성 할 Fragment 의 개수
    override fun getCount() = 5

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

}