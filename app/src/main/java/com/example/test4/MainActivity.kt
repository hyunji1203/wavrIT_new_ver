package com.example.test4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar_text : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // default 화면
        setContentView(R.layout.activity_main)

        // 처음으로 보이는 프래그먼트 홈 화면으로 설정하기
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, HomeFragment())
        transaction.commit()


        // 커스텀 툴바 사용
        toolbar_text = findViewById(R.id.toolbar_text)
        setSupportActionBar(findViewById(R.id.toolBar))

        supportActionBar!!.setDisplayShowTitleEnabled(false)

        // 하단바 사용
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu) // 작성한 아이콘
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
//            R.id.action_sidemenu -> {
//
//
//            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 하단바 페이지 바꾸기
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {

            R.id.action_home -> {
                toolbar_text.text = "여기로와"

                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, HomeFragment())
                transaction.commit()
                return true
            }
            R.id.action_consumption -> {
                toolbar_text.text = "매거진"

                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, MagazineFragment())
                transaction.commit()
                return true
            }
            R.id.action_trade -> {
                toolbar_text.text = "커뮤니티"

                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, CommunityFragment())
                transaction.commit()
                return true
            }
        }
        return false
    }
}