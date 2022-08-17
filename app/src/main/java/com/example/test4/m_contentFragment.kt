package com.example.test4

import android.content.ContentValues
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
import androidx.annotation.Dimension
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class m_contentFragment : Fragment(), onBackPressedListener, TextToSpeech.OnInitListener {

    lateinit var c_content : TextView
    lateinit var c_title : TextView
    lateinit var c_image : ImageView

    lateinit var auth: FirebaseAuth
    val database = Firebase.database

    private var tts: TextToSpeech? = null
    private var speechRecognizer: SpeechRecognizer? = null
    private val REQUEST_CODE = 1


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_m_content, container, false)

        auth = FirebaseAuth.getInstance()
        var sy3 = view.findViewById<TextView>(R.id.sy3)

        c_title = view.findViewById(R.id.c_title)
        c_content = view.findViewById(R.id.c_content)
        c_image = view.findViewById<ImageView>(R.id.c_image)

        val content = arguments?.getString("content")
        val title = arguments?.getString("title")
        val imageurl = arguments?.getString("imageurl")

        c_content.text = content.toString()
        c_title.text = title.toString()

        //c_content.setTextSize(Dimension.SP, 20F)

        Glide.with(this)
            .load(imageurl)
            .into(c_image)

        var key = auth.currentUser?.uid.toString()

        var myRef2 = database.getReference("users").child(key).child("text")
        //특정 데이터 값 갖고 오기!
        //리얼타임 데이터베이스 읽기
        myRef2.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot?.value
                sy3.text = value.toString()
                var a = sy3.text

                if (a == "24"){
                    c_content.setTextSize(Dimension.SP, 24F)
                }else if (a == "20"){
                    c_content.setTextSize(Dimension.SP, 20F)
                }else{
                    c_content.setTextSize(Dimension.SP, 16F)
                }
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
                sy3.text = value.toString()
                var a = sy3.text

                if (a == "1"){
                    c_title.setOnClickListener{tts!!.speak(c_title.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    c_content.setOnClickListener{tts!!.speak(c_content.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                   }

            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        // tts에 TextToSpeech 값 넣어줌
        tts = TextToSpeech(view.context, this)

        return view
    }

    override fun onBackPressed() {
        /*if (this is HomeFragment){
            activity?.finish()
        }*/

        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        fragmentManager?.popBackStack()
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