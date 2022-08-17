package com.example.test4.home.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.test4.R
import com.example.test4.m_contentFragment

class Recommend_thirdFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_recommend_third, container, false)

        var reco1 = view.findViewById<ImageView>(R.id.reco1)

        reco1.setOnClickListener {
            var mContentfragment = m_contentFragment()
            var bundle = Bundle()
            bundle.putString("title", "장애인 부부를 위한 임신 출산 메뉴얼")
            bundle.putString("content", "보건 복지부에서 엄마가 되고싶은 장애인을 위해 40주의 우주를 발간했습니다!.\n\n출산에 이르는 40주 동안에 피룡한 의학정보와 함께 장애유형별로 궁금한 내용들로 구성되어 있습니다. 임신, 출산을 하려는 장애여성을 대하는 의료인에게 실질적인 도움이 될 것 입니다.")
            bundle.putString("imageurl", "https://firebasestorage.googleapis.com/v0/b/waveit-test.appspot.com/o/activity%2F%EC%9E%84%EC%8B%A04.png?alt=media&token=6c66a780-b00e-4edd-bf03-e69833077c82")
            mContentfragment.arguments =
                bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, mContentfragment).addToBackStack(null)
                .commit()
        }

        return view
    }
}