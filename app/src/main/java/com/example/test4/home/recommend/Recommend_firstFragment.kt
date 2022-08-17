package com.example.test4.home.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.test4.R
import com.example.test4.m_contentFragment

class Recommend_firstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_recommend_first, container, false)

        var reco1 = view.findViewById<ImageView>(R.id.reco1)

        reco1.setOnClickListener {
            var mContentfragment = m_contentFragment()
            var bundle = Bundle()
            bundle.putString("title", "발달장애인 부모 성교육 프로그램")
            bundle.putString("content", "학령기 발달장애인 자녀를 둔 부모 5명을 대상으로 성교육 프로그램을 진행합니다.\"\n\n1학기와 2학기로 나뉘어져 발달장애인 자녀의 자기결정권 이해, 자녀 성교육시 부모의 역할과 태도 등을 교육합니다.")
            bundle.putString("imageurl", "https://firebasestorage.googleapis.com/v0/b/waveit-test.appspot.com/o/knowledge%2F%EC%84%B1%EA%B5%90%EC%9C%A14.jpg?alt=media&token=95b76d16-1964-43ef-8490-a903ae3bcc75")
            mContentfragment.arguments =
                bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, mContentfragment).addToBackStack(null)
                .commit()
        }

        return view
    }

}