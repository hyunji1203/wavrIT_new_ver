package com.example.test4.home.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test4.R
import com.example.test4.adapter.Mj_Adapter
import com.example.test4.adapter.firebase.ListViewModel
import com.example.test4.onBackPressedListener

class AddFragment : Fragment(), onBackPressedListener {

    private lateinit var add_adapter : Add_Adapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_add, container, false)

        var viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ListViewModel_add::class.java)

        add_adapter = Add_Adapter(this)

        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerview_add)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = add_adapter
        observerData(viewModel)

        return view
    }

    override fun onBackPressed() {
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        fragmentManager?.popBackStack()
    }

    fun observerData(viewModel: ListViewModel_add){
        viewModel.fetchData_add().observe(this, Observer {
            add_adapter.setListData(it)
            add_adapter.notifyDataSetChanged()
        })
    }

    fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
        requireActivity()
    } catch (e: IllegalStateException) {
        this
    }
}