package com.example.test4.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test4.Data
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class Repo_s {
    fun getData(): LiveData<MutableList<Data>> {

val mutableData = MutableLiveData<MutableList<Data>>()
        val database = Firebase.database
        val myRef = database.getReference("magazine").child("activity")
        val listData: MutableList<Data> = mutableListOf<Data>()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){

                        val getData = userSnapshot.getValue(Data::class.java)
                        listData.add(getData!!)
                        mutableData.value = listData
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {} })

        val myRef2 = database.getReference("magazine").child("knowledge")

        myRef2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val getData = userSnapshot.getValue(Data::class.java)
                        listData.add(getData!!)
                        mutableData.value = listData
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {} })

        val myRef3 = database.getReference("magazine").child("town")

        myRef3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val getData = userSnapshot.getValue(Data::class.java)
                        listData.add(getData!!)
                        mutableData.value = listData
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {} })

        val myRef4 = database.getReference("magazine").child("health")

        myRef4.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val getData = userSnapshot.getValue(Data::class.java)
                        listData.add(getData!!)
                        mutableData.value = listData
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {} })

        val myRef5 = database.getReference("magazine").child("job")

        myRef5.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val getData = userSnapshot.getValue(Data::class.java)
                        listData.add(getData!!)
                        mutableData.value = listData
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {} })


        return mutableData
    }
}