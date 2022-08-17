package com.example.test4.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.test4.MainActivity
import com.example.test4.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var database : FirebaseDatabase
    lateinit var databaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("users")

        var joinBtn1 = findViewById<ImageView>(R.id.joinBtn1)
        var emailArea1 = findViewById<EditText>(R.id.emailArea1)
        var passwordArea1 = findViewById<EditText>(R.id.passwordArea1)
        var passwordArea2 = findViewById<EditText>(R.id.passwordArea2)

        //binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

        joinBtn1.setOnClickListener {

            var isGoToJoin = true

            val email = emailArea1.text.toString()
            val password1 = passwordArea1.text.toString()
            val password2 = passwordArea2.text.toString()

            // 값이 비어있는 경우
            if(email.isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if(password1.isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if(password2.isEmpty()) {
                Toast.makeText(this, "비밀번호를 다시 한 번 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            // 비밀번호 확인
            if(!password1.equals(password2)) {
                Toast.makeText(this, "비밀번호를 똑같이 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            // 비밀번호가 자릿수
            if (password1.length < 6) {
                Toast.makeText(this, "8-20자리 사이의 비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if (password1.length > 20) {
                Toast.makeText(this, "8-20자리 사이의 비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            // 신규 회원 가입
            if(isGoToJoin) {

                auth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "환영합니다!", Toast.LENGTH_LONG).show()

                        var id = auth.currentUser?.uid
                        val user = user("서울", "지체장애", "0","16","")

                        //Data Inserted
                        databaseReference.child(id.toString()).setValue(user)

                        val intent = Intent(this, WaitActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)


                    } else {
                        Toast.makeText(this, "다시 한 번 확인해주세요", Toast.LENGTH_LONG).show()
                    }
                }

            }
        }

    }
}