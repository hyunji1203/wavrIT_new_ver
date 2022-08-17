package com.example.test4.utils

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class FBRef {

    companion object {

        private val database = Firebase.database

        val category1 = database.getReference("contents")
        val category2 = database.getReference("contents2")

        val bookmarkRef = database.getReference("bookmark_list")


        val commentRef = database.getReference("comment")


        val boardRef = database.getReference("board")


    }

}
