package com.example.test4.mypage

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.example.test4.Auth.user
import com.example.test4.Data
import com.example.test4.R
import com.example.test4.search.SearchActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class MypageActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    lateinit var auth: FirebaseAuth

    lateinit var database : FirebaseDatabase
    lateinit var databaseReference : DatabaseReference

    private var tts: TextToSpeech? = null
    private var speechRecognizer: SpeechRecognizer? = null
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        var move_fileter = findViewById<ImageView>(R.id.move_filter)
        var sy4 = findViewById<TextView>(R.id.sy4)
        var sy9 = findViewById<TextView>(R.id.sy9)

        var textView6 = findViewById<TextView>(R.id.textView6)
        var textView24 = findViewById<TextView>(R.id.textView24)
        var textView34 = findViewById<TextView>(R.id.textView34)


        auth = FirebaseAuth.getInstance()

        var id = findViewById<TextView>(R.id.mypage_id)
        var type = findViewById<TextView>(R.id.type)
        var where = findViewById<TextView>(R.id.where)
        var filter = findViewById<TextView>(R.id.filter)

        // 커스텀 툴바 사용
        //toolbar_text = findViewById(R.id.toolbar_text)
        setSupportActionBar(findViewById(R.id.toolBar2))

        supportActionBar!!.setDisplayShowTitleEnabled(false)

        var key = auth.currentUser?.uid.toString()

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("users").child(key).child("town")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val town = snapshot.getValue()
                where.text = town.toString()
            }
            override fun onCancelled(error: DatabaseError) {} })

        databaseReference = database.getReference("users").child(key).child("type")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val t = snapshot.getValue()
                type.text = t.toString()
            }
            override fun onCancelled(error: DatabaseError) {} })

        var s = "0"
        var t = "0"

        databaseReference = database.getReference("users").child(key).child("sound")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val x = snapshot.getValue().toString()
            }
            override fun onCancelled(error: DatabaseError) {} })

        databaseReference = database.getReference("users").child(key).child("text")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                t = snapshot.getValue().toString()
            }
            override fun onCancelled(error: DatabaseError) {} })

        if (s.toInt() == 1){
            filter.text = "음성지원"
        }
        else{
            filter.text = "글자크기"
        }

        var myRef2 = database.getReference("users").child(key).child("nickname")
        //특정 데이터 값 갖고 오기!
        //리얼타임 데이터베이스 읽기
        myRef2.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot?.value
                sy9.text = value.toString()
                var a = sy9.text

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
                sy4.text = value.toString()
                var a = sy4.text

                if (a == "1"){
                    id.setOnClickListener{tts!!.speak(id.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView6.setOnClickListener{tts!!.speak(textView6.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView24.setOnClickListener{tts!!.speak(textView24.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    textView34.setOnClickListener{tts!!.speak(textView34.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    where.setOnClickListener{tts!!.speak(where.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    filter.setOnClickListener{tts!!.speak(filter.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                    type.setOnClickListener{tts!!.speak(type.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")}
                }

            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        // tts에 TextToSpeech 값 넣어줌
        tts = TextToSpeech(this, this)


        move_fileter.setOnClickListener {
            var intent = Intent(this, FilterActivity::class.java)
            startActivity(intent)
        }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu) // 작성한 아이콘
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            R.id.action_search -> {
                var intent = Intent(this, SearchActivity::class.java)

                startActivity(intent)
            }
            R.id.login -> {
                var intent = Intent(this, MypageActivity::class.java)

                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}