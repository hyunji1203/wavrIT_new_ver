package com.example.test4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class Magazine_thirdFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_magazine_third, container, false)

        var hot1 = view.findViewById<ImageView>(R.id.hot1)

        hot1.setOnClickListener {
            var mContentfragment = m_contentFragment()
            var bundle = Bundle()
            bundle.putString("title", "10대 발달장애인 섹슈얼리티를 고민하기 위한 가이드북")
            bundle.putString("content", "10대 발달장애인 섹슈얼리티를 고민하기 위한 가이드북을 장애여성공감에서 만들었습니다.\n\n발달 장애인의 성적 권리에 대해서 알아보고 구체적인 사례로 드는 고민들을 통해 성적인 고민들을 해결해보세요")
            bundle.putString("imageurl", "https://firebasestorage.googleapis.com/v0/b/waveit-test.appspot.com/o/knowledge%2F%EC%84%B1%EA%B5%90%EC%9C%A12.jpg?alt=media&token=e4a91724-20c8-40b5-9b44-7d7d3466ff55")
            mContentfragment.arguments =
                bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, mContentfragment).addToBackStack(null)
                .commit()
        }

        return view
    }
}