package com.example.test4.adapter.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test4.Data

class ListViewModel_o   : ViewModel() {

    private val repo = Repo_o()

    fun fetchData_o(): LiveData<MutableList<Data>> {

        val mutableData = MutableLiveData<MutableList<Data>>()

        repo.getData().observeForever{
            mutableData.value = it
        }

        return mutableData
    }
}