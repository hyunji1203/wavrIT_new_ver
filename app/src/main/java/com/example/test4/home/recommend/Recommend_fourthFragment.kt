package com.example.test4.home.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.test4.R
import com.example.test4.m_contentFragment

class Recommend_fourthFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_recommend_fourth, container, false)

        var reco1 = view.findViewById<ImageView>(R.id.reco1)

        reco1.setOnClickListener {
            var mContentfragment = m_contentFragment()
            var bundle = Bundle()
            bundle.putString("title", "여성장애인의 출산비용을 지원합니다.")
            bundle.putString("content", "성남시에서 여성장애인 출산 비용을 지원합니다.\n\n정부 지원 100만원+성남시 100만원 까지 지원받을수 있어요. .\n\n동 행정복지센터에서 지금바로 신청하세요!")
            bundle.putString("imageurl", "https://firebasestorage.googleapis.com/v0/b/waveit-test.appspot.com/o/activity%2F%EC%9E%84%EC%8B%A03.jpg?alt=media&token=c0505187-5806-4bd9-b08d-b5f52211143d")
            mContentfragment.arguments =
                bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, mContentfragment).addToBackStack(null)
                .commit()
        }

        return view
    }
}