package com.example.test4.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test4.Data
import com.example.test4.adapter.firebase.Repo

class ListViewModel_cl  : ViewModel() {

    private val repo = Repo_cl()

    fun fetchData_cl(): LiveData<MutableList<data_cl>> {

        val mutableData = MutableLiveData<MutableList<data_cl>>()

        repo.getData().observeForever{
            mutableData.value = it
        }

        return mutableData
    }
}