package com.example.test4.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test4.Data
import com.example.test4.adapter.firebase.Repo_a
import com.example.test4.adapter.firebase.Repo_o

class ListViewModel_s  : ViewModel() {

    private val repo = Repo_s()

    fun fetchData_s(): LiveData<MutableList<Data>> {

        val mutableData = MutableLiveData<MutableList<Data>>()

        repo.getData().observeForever{
            mutableData.value = it
        }

        return mutableData
    }
}