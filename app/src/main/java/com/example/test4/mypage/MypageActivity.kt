package com.example.test4.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class MypageActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    lateinit var database : FirebaseDatabase
    lateinit var databaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        var move_fileter = findViewById<ImageView>(R.id.move_filter)

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
                s = snapshot.getValue().toString()
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



        id.text = auth.currentUser?.email


        move_fileter.setOnClickListener {
            var intent = Intent(this, FilterActivity::class.java)
            startActivity(intent)
        }
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