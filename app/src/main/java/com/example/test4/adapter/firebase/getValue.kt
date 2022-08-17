package com.example.test4.adapter.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class getValue {
    lateinit var database : FirebaseDatabase
    lateinit var databaseReference : DatabaseReference


    fun getvalue(key : String) : String{

        var sound = ""

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("users").child(key).child("town")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                sound = snapshot.getValue() as String

            }
            override fun onCancelled(error: DatabaseError) {} })
        return sound
    }


}