package com.example.test4.search

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test4.Data
import com.example.test4.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class SearchActivity : AppCompatActivity() {

    //리스트 선언
    var search_List =  ArrayList<search_data>()

    //데이터 베이스 사용을 위해 변수 선언
    lateinit var dbManager : DBManager
    lateinit var sqlitedb : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        var s_back_btn = findViewById<ImageView>(R.id.s_back_btn)
        var search_btn = findViewById<TextView>(R.id.search_btn)

        val Recyclerview_s = findViewById<RecyclerView>(R.id.Recyclerview_s)



        var filledTextField : TextInputLayout = findViewById(R.id.filledTextField)

        val textInputLayout = TextInputLayout(this)

        search_btn.setOnClickListener {
            // Get input text
            val inputText = filledTextField.editText?.text.toString()

            var intent = Intent(this, Search_Result_Activity::class.java)
            intent.putExtra("searchword", inputText)
            startActivity(intent)
        }

        //알람 설정이 된 장학금 리스트들을 저장할 데이터베이스를 불러옴
        dbManager = DBManager(this, "search_history", null, 1)
        sqlitedb = dbManager.readableDatabase


        //현재 알람설정 데이터베이스에 저장된 리스트들을 데려와 profileList3에 저장하는 코드
        var cursor : Cursor = sqlitedb.rawQuery("SELECT * FROM search_history", null)

        while (cursor.moveToNext()){
            var first = cursor.getString((cursor.getColumnIndex("word")))
            var second = cursor.getInt((cursor.getColumnIndex("count")))

            search_List.add(search_data(first,second))

        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

        Recyclerview_s.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        Recyclerview_s.setHasFixedSize(true)

        Recyclerview_s.adapter = searchAdapter(search_List)

        //profileList3 디데이 작은 순으로 정렬하기기
        /*profileList3.sortBy { it.value2 }

        //Adapter를 활용하여 list와 XML 이어주기
        rvs_profile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvs_profile.setHasFixedSize(true)

        rvs_profile.adapter = ProfileAdapter3(profileList3)*/


        s_back_btn.setOnClickListener {
            finish()
        }
    }
}