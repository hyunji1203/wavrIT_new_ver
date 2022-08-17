package com.example.test4.mypage

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.test4.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.*

class FilterActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    lateinit var example : TextView

    private lateinit var auth: FirebaseAuth

    lateinit var database : FirebaseDatabase
    lateinit var databaseReference : DatabaseReference

    private var tts: TextToSpeech? = null
    private var speechRecognizer: SpeechRecognizer? = null
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        var sound_switch = findViewById<Switch>(R.id.sound_switch)
        var size_minus = findViewById<ImageView>(R.id.size_minus)
        var size_plus = findViewById<ImageView>(R.id.size_plus)

        sound_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                auth = Firebase.auth
                var key = auth.currentUser?.uid.toString()

                database = FirebaseDatabase.getInstance()
                databaseReference = database.getReference("users").child(key).child("sound")

                databaseReference.setValue(1)
            }
            else {
                auth = Firebase.auth
                var key = auth.currentUser?.uid.toString()

                database = FirebaseDatabase.getInstance()
                databaseReference = database.getReference("users").child(key).child("sound")

                databaseReference.setValue(0)
            }
        }

        example = findViewById(R.id.example)

        // permission 확인
        //if (Build.VERSION.SDK_INT >= 23)
        //    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET, Manifest.permission.RECORD_AUDIO), REQUEST_CODE)

        // tts에 TextToSpeech 값 넣어줌
        tts = TextToSpeech(this, this)

        // TTSButton 클릭시 startTTS() 함수 실행
        example.setOnClickListener { tts!!.speak(example.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}//startTTS() }



    }


    // TTS 예제
    /*private fun startTTS() {
        tts!!.speak(example.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")
    }*/

    // TextToSpeech override 함수
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.KOREA)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                //doSomething
            } else {
                //doSomething
            }
        } else {
            //doSomething
        }

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
