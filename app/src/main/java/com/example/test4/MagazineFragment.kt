package com.example.test4

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.example.test4.adapter.ViewPagerAdapter_magazine
import com.example.test4.magazine.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class MagazineFragment : Fragment(), TextToSpeech.OnInitListener {

    lateinit var auth: FirebaseAuth
    val database = Firebase.database

    private var tts: TextToSpeech? = null
    private var speechRecognizer: SpeechRecognizer? = null
    private val REQUEST_CODE = 1


    lateinit var ourtown_btn : ImageView
    lateinit var job_btn : ImageView
    lateinit var health_btn : ImageView
    lateinit var activity_btn : ImageView
    lateinit var knowledge_btn : ImageView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_magazine, container, false)

        auth = FirebaseAuth.getInstance()

        ourtown_btn = view.findViewById(R.id.ourtown_btn)
        job_btn = view.findViewById(R.id.job_btn)
        health_btn = view.findViewById(R.id.health_btn)
        activity_btn = view.findViewById(R.id.activity_btn)
        knowledge_btn = view.findViewById(R.id.knowledge_btn)

        var textView2 = view.findViewById<TextView>(R.id.textView2)
        var textView = view.findViewById<TextView>(R.id.textView)
        var textView4 = view.findViewById<TextView>(R.id.textView4)
        var textView5 = view.findViewById<TextView>(R.id.textView5)
        var textView18 = view.findViewById<TextView>(R.id.textView18)
        var textView19 = view.findViewById<TextView>(R.id.textView19)
        var textView20 = view.findViewById<TextView>(R.id.textView20)
        var textView21 = view.findViewById<TextView>(R.id.textView21)
        var textView22 = view.findViewById<TextView>(R.id.textView22)

        var id = view.findViewById<TextView>(R.id.mypage_id)

        var sy2 = view.findViewById<TextView>(R.id.sy2)
        var sy8 = view.findViewById<TextView>(R.id.sy8)

        // viewpager어댑터
        val viewPager_magazine = view.findViewById<ViewPager>(R.id.viewPager_magazine)
        // 슬라이드를 위한 어댑터 설정
        val pagerAdapter_middle = ViewPagerAdapter_magazine(childFragmentManager)
        viewPager_magazine.adapter = pagerAdapter_middle

        // 옆으로 넘어갈 때 이미지가 어떻게 보이는지 설정
        val dpValue: Int = 50
        val d: Float = getResources().getDisplayMetrics().density
        val margin: Int = dpValue * d.toInt()
        viewPager_magazine.setClipToPadding(false)
        viewPager_magazine.setPadding(margin, 0, margin, 0);
        viewPager_magazine.setPageMargin(30);

        ourtown_btn.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout, OurtownFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
        job_btn.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout, JobFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
        activity_btn.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            //transaction?.replace(R.id.frameLayout, ActivityFragment())
            transaction?.replace(R.id.frameLayout, ActivityFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
        health_btn.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout, HealthFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
        knowledge_btn.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frameLayout, KnowledgeFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        var key = auth.currentUser?.uid.toString()

        var myRef2 = database.getReference("users").child(key).child("nickname")
        //특정 데이터 값 갖고 오기!
        //리얼타임 데이터베이스 읽기
        myRef2.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot?.value
                sy8.text = value.toString()
                var a = sy8.text

                a = "$a 님의\n맞춤 매거진을 보여드릴께요"

                id.text = a
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
                sy2.text = value.toString()
                var a = sy2.text

                if (a == "1"){
                    id.setOnClickListener{tts!!.speak(id.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView.setOnClickListener{tts!!.speak(textView.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView2.setOnClickListener{tts!!.speak(textView2.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView4.setOnClickListener{tts!!.speak(textView4.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView5.setOnClickListener{tts!!.speak(textView5.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView18.setOnClickListener{tts!!.speak(textView18.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView19.setOnClickListener{tts!!.speak(textView19.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView20.setOnClickListener{tts!!.speak(textView20.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView21.setOnClickListener{tts!!.speak(textView21.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView22.setOnClickListener{tts!!.speak(textView22.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                }

            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        // tts에 TextToSpeech 값 넣어줌
        tts = TextToSpeech(view.context, this)

        //tts!!.speak(textView9.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")

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