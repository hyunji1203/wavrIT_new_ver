package com.example.test4.home.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test4.Data
import com.example.test4.search.Repo_s

class ListViewModel_add  : ViewModel() {

    private val repo = Repo_add()

    fun fetchData_add(): LiveData<MutableList<Data>> {

        val mutableData = MutableLiveData<MutableList<Data>>()

        repo.getData().observeForever{
            mutableData.value = it
        }

        return mutableData
    }
}