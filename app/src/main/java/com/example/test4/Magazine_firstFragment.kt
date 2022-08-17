package com.example.test4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class Magazine_firstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_magazine_first, container, false)

        var hot1 = view.findViewById<ImageView>(R.id.hot1)

        hot1.setOnClickListener {
            var mContentfragment = m_contentFragment()
            var bundle = Bundle()
            bundle.putString("title", "서울시 특화프로그램의 수강생을 모집합니다")
            bundle.putString("content", "서울시 장애여성 인력 개발센터에서  특화프로그램 수강생을 모집합니다.\n\n글쓰기 프로그램, 청소정리 꿀팀 프로그램, 장애니 인색개선교육강사 양성과정 프로그램 등에 참여해보세요.\n\n자세한 문의는 02-6929-0002")
            bundle.putString("imageurl", "https://firebasestorage.googleapis.com/v0/b/waveit-test.appspot.com/o/town%2F%EC%9A%B0%EB%A6%AC%EB%8F%99%EB%84%A41.jpg?alt=media&token=b275b61c-ed4d-458e-9438-141f669f714b")
            mContentfragment.arguments =
                bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, mContentfragment).addToBackStack(null)
                .commit()
        }

        return view
    }
}