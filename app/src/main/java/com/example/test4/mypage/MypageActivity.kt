package com.example.test4.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.example.test4.R
import com.example.test4.search.SearchActivity

class MypageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        var move_fileter = findViewById<ImageView>(R.id.move_filter)

        // 커스텀 툴바 사용
        //toolbar_text = findViewById(R.id.toolbar_text)
        setSupportActionBar(findViewById(R.id.toolBar2))

        supportActionBar!!.setDisplayShowTitleEnabled(false)

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