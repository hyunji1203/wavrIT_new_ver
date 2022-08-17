package com.example.test4.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.test4.R

import com.example.test4.databinding.ActivityBoardWriteBinding
import com.example.test4.utils.FBAuth

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBoardWriteBinding

    private val TAG = BoardWriteActivity::class.java.simpleName

    private var isImageUpload = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_write)

        binding.writeBtn.setOnClickListener {

            val title = binding.titleArea.text.toString()
            val content = binding.contentArea.text.toString()
            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()

            Log.d(TAG, title)
            Log.d(TAG, content)
        }

        // 파이어베이스 store에 이미지를 저장
        // 만약에 내가 게시글을 클릭했을 때, 게시글에 대한 정보를 받아와야 하는데
        // 이미지 이름에 대한 정보를 모르기 때문에
        // 이미지 이름을 문서의 key값으로 해줘서 이미지에 대한 정보를 찾기 쉽게 해놓음.
    }}