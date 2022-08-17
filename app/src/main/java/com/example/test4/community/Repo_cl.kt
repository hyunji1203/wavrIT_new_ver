package com.example.test4.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test4.Data
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Repo_cl {
    fun getData(): LiveData<MutableList<data_cl>> {

        val mutableData = MutableLiveData<MutableList<data_cl>>()
        val database = Firebase.database
        val myRef = database.getReference("community")

        myRef.addValueEventListener(object : ValueEventListener {

            val listData: MutableList<data_cl> = mutableListOf<data_cl>()

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){

                        val getData = userSnapshot.getValue(data_cl::class.java)
                        listData.add(getData!!)
                        mutableData.value = listData
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
        return mutableData
    }
}