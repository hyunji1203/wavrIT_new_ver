package com.example.test4.home.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.test4.R
import com.example.test4.m_contentFragment

class Recommend_secondFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_recommend_second, container, false)

        var reco1 = view.findViewById<ImageView>(R.id.reco1)

        reco1.setOnClickListener {
            var mContentfragment = m_contentFragment()
            var bundle = Bundle()
            bundle.putString("title", "서울시 특화프로그램 수강생모집")
            bundle.putString("content", "경기도 일자리 재단에서 장애인을 위한 취업 박람회가 열렸습니다.\n\n스마트 산업분야에서 잡매칭, 동행면접, 컨설팅 서비스를 받을 수 있는 기회입니다.\n\n경기도장애인취업 박람회 신청하고 다양한 혜택과 기회를 누려보세요!")
            bundle.putString("imageurl", "https://firebasestorage.googleapis.com/v0/b/waveit-test.appspot.com/o/job%2F%EC%B7%A8%EC%97%851.jpg?alt=media&token=bac55bd6-8bf3-48bc-a74b-cc3feafb7117")
            mContentfragment.arguments =
                bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, mContentfragment).addToBackStack(null)
                .commit()
        }

        return view
    }
}