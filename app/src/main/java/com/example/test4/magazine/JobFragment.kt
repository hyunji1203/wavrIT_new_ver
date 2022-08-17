package com.example.test4.magazine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import com.example.test4.adapter.firebase.ListViewModel
import com.example.test4.adapter.Mj_Adapter
import com.example.test4.R
import com.example.test4.onBackPressedListener

class JobFragment : Fragment(), onBackPressedListener {

    private lateinit var mj_adapter : Mj_Adapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_job, container, false)

        var viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ListViewModel::class.java)

        mj_adapter = Mj_Adapter(this)

        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = mj_adapter
        observerData(viewModel)

        return view
    }

    override fun onBackPressed() {
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        fragmentManager?.popBackStack()
    }

    fun observerData(viewModel: ListViewModel){
        viewModel.fetchData().observe(this, Observer {
            mj_adapter.setListData(it)
            mj_adapter.notifyDataSetChanged()
        })
    }

    fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
        requireActivity()
    } catch (e: IllegalStateException) {
        this
    }
}