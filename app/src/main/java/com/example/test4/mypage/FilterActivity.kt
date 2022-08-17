package com.example.test4.mypage

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Dimension
import androidx.core.app.ActivityCompat
import com.example.test4.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.util.*

class FilterActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    lateinit var example : TextView
    
    lateinit var auth: FirebaseAuth

    lateinit var database : FirebaseDatabase
    lateinit var databaseReference : DatabaseReference

    private var tts: TextToSpeech? = null
    private var speechRecognizer: SpeechRecognizer? = null
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        var textView2 = findViewById<TextView>(R.id.textView2)
        var mypage_id = findViewById<TextView>(R.id.mypage_id)
        var textView35 = findViewById<TextView>(R.id.textView35)
        var textView36 = findViewById<TextView>(R.id.textView36)

        var sound_switch = findViewById<Switch>(R.id.sound_switch)
        var size_minus = findViewById<ImageView>(R.id.size_minus)
        var size_plus = findViewById<ImageView>(R.id.size_plus)
        var sp = findViewById<TextView>(R.id.sp)

        auth = FirebaseAuth.getInstance()

        var sy5 = findViewById<TextView>(R.id.sy5)
        var sy6 = findViewById<TextView>(R.id.sy6)
        var key = auth.currentUser?.uid.toString()

        sound_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                auth = Firebase.auth

                database = FirebaseDatabase.getInstance()
                databaseReference = database.getReference("users").child(key).child("sound")

                databaseReference.setValue(1)
            }
            else {
                auth = Firebase.auth

                database = FirebaseDatabase.getInstance()
                databaseReference = database.getReference("users").child(key).child("sound")

                databaseReference.setValue(0)
            }
        }

        database = FirebaseDatabase.getInstance()

        var myRef1 = database.getReference("users").child(key).child("sound")
        //특정 데이터 값 갖고 오기!
        //리얼타임 데이터베이스 읽기
        myRef1.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot?.value
                sy5.text = value.toString()
                var a = sy5.text

                if (a == "1"){
                    mypage_id.setOnClickListener{tts!!.speak(mypage_id.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView2.setOnClickListener{tts!!.speak(textView2.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView35.setOnClickListener{tts!!.speak(textView35.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView36.setOnClickListener{tts!!.speak(textView36.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}

                    sound_switch.isChecked = true
                }
                else{
                    sound_switch.isChecked = false
                }

            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        // tts에 TextToSpeech 값 넣어줌
        tts = TextToSpeech(this, this)

        /////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////
        var myRef2 = database.getReference("users").child(key).child("text")
        //특정 데이터 값 갖고 오기!
        //리얼타임 데이터베이스 읽기
        myRef2.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot?.value
                sy6.text = value.toString()
                var a = sy6.text

                if (a == "24"){
                    sp.text = "24"
                }else if (a == "20"){
                    sp.text = "20"
                }else{
                    sp.text = "16"
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        //텍스트 크기 조정
        size_plus.setOnClickListener {
            if(sp.text == "16"){
                sp.text = "20"
                databaseReference = database.getReference("users").child(key).child("text")
                databaseReference.setValue("20")
            }
            else{
                sp.text = "24"
                databaseReference = database.getReference("users").child(key).child("text")
                databaseReference.setValue("24")
            }
        }
        size_minus.setOnClickListener {
            if(sp.text == "24"){
                sp.text = "20"
                databaseReference = database.getReference("users").child(key).child("text")
                databaseReference.setValue("20")
            }
            else{
                sp.text = "16"
                databaseReference = database.getReference("users").child(key).child("text")
                databaseReference.setValue("16")
            }
        }

    }


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
