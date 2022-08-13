package com.example.test4.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test4.community.my_community_Fragment
import com.example.test4.community.whole_community_Fragment

class ViewPagerAdapter_community (fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return   when(position){
            0->{
                whole_community_Fragment() // frag_daily()
            }
            1->{
                my_community_Fragment() // month_daily()
            }
            else->{
                whole_community_Fragment() // ?
            }

        }
    }
}