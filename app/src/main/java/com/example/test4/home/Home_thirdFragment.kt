package com.example.test4.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.test4.R
import com.example.test4.m_contentFragment

class Home_thirdFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_home_third, container, false)

        var home3 = view.findViewById<ImageView>(R.id.home3)

        home3.setOnClickListener {
            var mContentfragment = m_contentFragment()
            var bundle = Bundle()
            bundle.putString("title", "발달장애인 성교육 지원을 위한 \n<발달장애인과 성> 2기 모집")
            bundle.putString("content", "발달장애인 성교육 지원을 위한 <발달장애인과 성> 2기를 모집합니다!\n\n본 교육은 생애주기별 성적 발달 특성 및 성적 자기결정권을 이해하고, 실제 업무 현장에서 직면하고 있는 사례를 연구하는 등의 발달장애인 성교육을 지원합니다!\n\n 경기복지평생원에서 온라인으로 신청받고 있습니다.")
            bundle.putString("imageurl", "https://firebasestorage.googleapis.com/v0/b/waveit-test.appspot.com/o/knowledge%2F%EC%84%B1%EA%B5%90%EC%9C%A11.png?alt=media&token=2f2863ad-5feb-48d9-b6b9-15c419b5b387")
            mContentfragment.arguments =
                bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, mContentfragment).addToBackStack(null)
                .commit()
        }

        return view

    }
}