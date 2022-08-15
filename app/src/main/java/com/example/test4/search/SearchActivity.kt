package com.example.test4.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test4.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class SearchActivity : AppCompatActivity() {

    val database = Firebase.database
    val myRef = database.getReference("magazine")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        var search_btn = findViewById<ImageView>(R.id.search_btn)

        val Recyclerview_s = findViewById<RecyclerView>(R.id.Recyclerview_s)

        //Recyclerview_s.adapter = RecyclerViewAdapter()
        //Recyclerview_s.layoutManager = LinearLayoutManager(this)

        //
        var filledTextField : TextInputLayout = findViewById(R.id.filledTextField)

        val textInputLayout = TextInputLayout(this)
        val editText = TextInputEditText(textInputLayout.context)

        // Get input text
        val inputText = filledTextField.editText?.text.toString()

        search_btn.setOnClickListener {
            var intent = Intent(this, Search_Result_Activity::class.java)
            intent.putExtra("searchword", inputText)
            startActivity(intent)
        }
    }



    /*inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


        // xml파일을 inflate하여 ViewHolder를 생성
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            return ViewHolder(view)
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        }

        // onCreateViewHolder에서 만든 view와 실제 데이터를 연결
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewHolder = (holder as ViewHolder).itemView
        }

        // 리사이클러뷰의 아이템 총 개수 반환
        override fun getItemCount(): Int {
            return 1 //suburbs_list.size
        }
    }*/

}