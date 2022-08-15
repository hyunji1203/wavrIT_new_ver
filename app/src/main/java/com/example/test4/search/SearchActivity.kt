package com.example.test4.search

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test4.MainActivity
import com.example.test4.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.*


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
        var search_btn = findViewById<ImageView>(R.id.search_btn)
        var all_delete = findViewById<TextView>(R.id.all_delete)

        val Recyclerview_s = findViewById<RecyclerView>(R.id.Recyclerview_s)
        val RecyclerView_hot = findViewById<RecyclerView>(R.id.Recyclerview_hot)

        var word : String = "word"

        var filledTextField : TextInputLayout = findViewById(R.id.filledTextField)

        //val textInputLayout = TextInputLayout(this)

        all_delete.setOnClickListener {
            sqlitedb = dbManager.writableDatabase
            var table_name = "search_history"
            sqlitedb.execSQL("delete from " + table_name)

            var intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        search_btn.setOnClickListener {

            // Get input text
            val inputText = filledTextField.editText?.text.toString()

            sqlitedb = dbManager.readableDatabase

            var cursor : Cursor
            cursor = sqlitedb.rawQuery("SELECT * FROM search_history WHERE word = '"+inputText+"';", null)

            if (cursor.count > 0){
                var x = 0

                while (cursor.moveToNext()) {
                    x = cursor.getInt((cursor.getColumnIndex("count")))
                    x = x + 1
                }

                sqlitedb = dbManager.writableDatabase
                sqlitedb.execSQL("UPDATE search_history SET count = '"+x+"' WHERE word = '"+inputText+"';")
            }
            else{
                sqlitedb = dbManager.writableDatabase
                sqlitedb.execSQL("INSERT INTO search_history VALUES ('" +inputText+ "', '" +1+ "')")
            }

            sqlitedb.close()
            dbManager.close()

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

        val search_hot: ArrayList<search_data> = ArrayList()
        search_hot.addAll(search_List)

        //실시간 인기 검색어 정렬하기
        search_hot.sortBy {it.count }
        search_hot.reverse()

        //Adapter를 활용하여 list와 XML 이어주기
        RecyclerView_hot.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        RecyclerView_hot.setHasFixedSize(true)

        RecyclerView_hot.adapter = searchhotAdapter(search_hot)


        s_back_btn.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}