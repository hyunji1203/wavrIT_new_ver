package com.example.test4.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.test4.R
import com.example.test4.m_contentFragment

class Home_secondFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_home_second, container, false)

        var home2 = view.findViewById<ImageView>(R.id.home2)

        home2.setOnClickListener {
            var mContentfragment = m_contentFragment()
            var bundle = Bundle()
            bundle.putString("title", "서울 여성장애인 성폭력 상담소를 소개합니다!")
            bundle.putString("content", "장애여성공감 성폭력 상담소는 우리 사회에서 장애 여성에게 가해지는 성폭력에 대하여 장애와 젠더 관점으로 성찰하고, 성폭력 범죄 피해자에 대한 적극적인 지지와 더불어 다양한 방식으로 지원을 하고 있습니다. 이는 장애와 여성에 대한 차별과 폭력에 맞서 성적 주체로서의 장애 여성의 인권을 옹호하는 활동임과 동시에 이를 침해하는 모든 범죄 행위에 대한 피해자의 정당한 대응을 조력하는 활동입니다. .\n\n성폭력 피해 장애 여성을 위한 상담 및 직접적인 지원 활동과 장애 여성 인권 향상을 위한 조사연구 및 정책제안 활동도 하고 있습니다.\n\n언제든지 연락주세요.\n\n상담전화 02-3013-1367")
            bundle.putString("imageurl", "https://firebasestorage.googleapis.com/v0/b/waveit-test.appspot.com/o/knowledge%2F%EC%84%B1%EA%B5%90%EC%9C%A13.png?alt=media&token=f9080870-bb75-4c6e-87f7-8233c5bf70f0")
            mContentfragment.arguments =
                bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, mContentfragment).addToBackStack(null)
                .commit()
        }

        return view
    }
}