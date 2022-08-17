package com.example.test4

import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.example.test4.adapter.ViewPagerAdapter_home
import com.example.test4.adapter.ViewPagerAdapter_home_recommend
import com.example.test4.adapter.firebase.getValue
import com.example.test4.home.recommend.AddFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.*

class HomeFragment : Fragment(), TextToSpeech.OnInitListener {

    lateinit var auth: FirebaseAuth

    private var tts: TextToSpeech? = null
    private var speechRecognizer: SpeechRecognizer? = null
    private val REQUEST_CODE = 1

    lateinit var textView9 : TextView
    lateinit var textView29 : TextView
    lateinit var textView31 : TextView
    lateinit var add1 : TextView
    lateinit var add2 : ImageView

    val database = Firebase.database

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_home, container, false)

        add1 = view.findViewById<TextView>(R.id.add1)
        add2 = view.findViewById<ImageView>(R.id.add2)
        textView9 = view.findViewById<TextView>(R.id.h1)
        textView29 = view.findViewById<TextView>(R.id.textView29)
        textView31 = view.findViewById<TextView>(R.id.textView31)
        var sy = view.findViewById<TextView>(R.id.sy)
        var sy7 = view.findViewById<TextView>(R.id.sy7)

        var home_id = view.findViewById<TextView>(R.id.home_id)

        auth = FirebaseAuth.getInstance()
        //database = FirebaseDatabase.getInstance()

        //home_id.text = auth.currentUser?.email

        // viewpager 어댑터1
        val viewPager_home = view.findViewById<ViewPager>(R.id.viewPager_magazine)
        // 슬라이드를 위한 어댑터 설정
        val pagerAdapter_home = ViewPagerAdapter_home(childFragmentManager)
        viewPager_home.adapter = pagerAdapter_home

        // viewpager 어댑터2
        val viewPager_home_recommend = view.findViewById<ViewPager>(R.id.viewPager_home)
        // 슬라이드를 위한 어댑터 설정
        val pagerAdapter_recommend = ViewPagerAdapter_home_recommend(childFragmentManager)
        viewPager_home_recommend.adapter = pagerAdapter_recommend


        // 배너 옆으로 넘어갈 때 이미지가 어떻게 보이는지 설정
        val dpValue1: Int = 0
        val d1: Float = resources.displayMetrics.density
        val margin1: Int = dpValue1 * d1.toInt()
        viewPager_home.clipToPadding = false
        viewPager_home.setPadding(margin1, 0, margin1, 0);
        viewPager_home.pageMargin = 30


        // 추천 정보 옆으로 넘어갈 때 이미지가 어떻게 보이는지 설정
        val dpValue2: Int = 200
        val d2: Float = resources.displayMetrics.density
        val margin2: Int = dpValue2 * d2.toInt()
        viewPager_home_recommend.clipToPadding = false
        viewPager_home_recommend.setPadding(0, 0, margin2, 0);
        viewPager_home_recommend.pageMargin = 50

        add1.setOnClickListener {
            var AddFragment = AddFragment()

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, AddFragment).addToBackStack(null)
                .commit()
        }
        add2.setOnClickListener {
            var AddFragment = AddFragment()

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.frameLayout, AddFragment).addToBackStack(null)
                .commit()
        }

        var key = auth.currentUser?.uid.toString()

        var myRef2 = database.getReference("users").child(key).child("nickname")
        //특정 데이터 값 갖고 오기!
        //리얼타임 데이터베이스 읽기
        myRef2.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot?.value
                sy7.text = value.toString()
                var a = sy7.text

                home_id.text = a
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        var myRef1 = database.getReference("users").child(key).child("sound")
        //특정 데이터 값 갖고 오기!
        //리얼타임 데이터베이스 읽기
        myRef1.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot?.value
                sy.text = value.toString()
                var a = sy.text

                if (a == "1"){
                    textView9.setOnClickListener{tts!!.speak(textView9.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView29.setOnClickListener{tts!!.speak(textView29.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView31.setOnClickListener{tts!!.speak(textView31.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    add1.setOnClickListener{tts!!.speak(add1.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                }

            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        // tts에 TextToSpeech 값 넣어줌
        tts = TextToSpeech(view.context, this)

        tts!!.speak(textView9.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")

        return view
    }

    // TextToSpeech override 함수
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.KOREA)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {} else {}} else {}
    }
    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        if (speechRecognizer != null) {
            speechRecognizer!!.stopListening()
        }
        super.onDestroy()
    }

}