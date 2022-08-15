package com.example.test4.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test4.Data
import com.example.test4.R
import com.example.test4.adapter.Ma_Adapter
import com.example.test4.adapter.firebase.ListViewModel_a
import com.example.test4.m_contentFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class Search_Result_Activity : AppCompatActivity() {

    lateinit var searchword : String
    private lateinit var s_adapter : S_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        var s_back_btn = findViewById<ImageView>(R.id.s_back_btn)
        var word = findViewById<Button>(R.id.word)

        searchword = intent.getStringExtra("searchword").toString()

        var viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ListViewModel_s::class.java)

        s_adapter = S_Adapter(this, searchword)

        word.text = searchword

        val recyclerView : RecyclerView = findViewById(R.id.RecyclerView_sr)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = s_adapter
        observerData(viewModel)


        s_back_btn.setOnClickListener {
            var intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }
    fun observerData(viewModel: ListViewModel_s){
        viewModel.fetchData_s().observe(this, Observer {
            s_adapter.setListData(it)
            s_adapter.notifyDataSetChanged()
        })
    }

    fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
        requireActivity()
    } catch (e: IllegalStateException) {
        this
    }
}