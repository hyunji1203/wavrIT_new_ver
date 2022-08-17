package com.example.test4.home.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.test4.R
import com.example.test4.m_contentFragment

class Recommend_fifthFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_recommend_fifth, container, false)

        var reco1 = view.findViewById<ImageView>(R.id.reco1)

        reco1.setOnClickListener {
            var mContentfragment = m_contentFragment()
            var bundle = Bundle()
            bundle.putString("title", "여성장애인 홈헬퍼 지원사업 이용자를 모집합니다.")
            bundle.putString("content", "에덴장애인종합복지관에서는 여성장애인 홈헬퍼 지원사업 이용자를 모집하고 있습니다!\n\n여성장애인 홈헬퍼 지원사업은 만 9세 미만의 자녀를 양육하고 있는 여성 장애인의 임신, 출산, 양육을 돕는 서비스로 전문 자격을 갖춘 홈헬퍼가 이용자 가정에 방문하여 서비스를 제공하는 사업입니다.\n\n이용비는 전액 무료이며 지원자는 유선상담을 진행해주세요. .\n\n사회서비스팀 신명숙 사회복지사 ) 02-2611-0588")
            bundle.putString("imageurl", "https://firebasestorage.googleapis.com/v0/b/waveit-test.appspot.com/o/activity%2F%EC%9E%84%EC%8B%A02.jpg?alt=media&token=6d1e2727-e426-480e-a275-8262b0980cbf")
            mContentfragment.arguments =
                bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, mContentfragment).addToBackStack(null)
                .commit()
        }

        return view
    }
}