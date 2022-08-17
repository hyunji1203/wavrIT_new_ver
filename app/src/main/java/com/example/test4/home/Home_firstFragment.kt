package com.example.test4.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.test4.R
import com.example.test4.m_contentFragment

class Home_firstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_home_first, container, false)

        var home1 = view.findViewById<ImageView>(R.id.home1)

        home1.setOnClickListener {
            var mContentfragment = m_contentFragment()
            var bundle = Bundle()
            bundle.putString("title", "향기 가득한 장애인의 날, 아로마 테라피")
            bundle.putString("content", "장애인의 날을 맞이하여 열린 새로운 국비지원 교육을 소개합니다.\n\n아로마테라피 수업으로 몸과 마음의 안정을 되찾을 수 있는 수업입니다.\n\n2022년 수요일 오전 10시부터 오후3시까지 입니다.\n\n수강자격은 18세 이상의 서울거주 장애인 (장애인 증명서를 제출하세요.)")
            bundle.putString("imageurl", "https://firebasestorage.googleapis.com/v0/b/waveit-test.appspot.com/o/health%2F%EA%B1%B4%EA%B0%951.jpg?alt=media&token=871639e6-658e-4996-a62f-c47b920202d9")
            mContentfragment.arguments =
                bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, mContentfragment).addToBackStack(null)
                .commit()
        }

        return view
    }
}