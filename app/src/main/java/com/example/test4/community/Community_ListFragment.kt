package com.example.test4.community

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
import com.example.test4.adapter.Ma_Adapter
import com.example.test4.adapter.firebase.ListViewModel_a
import com.example.test4.onBackPressedListener


class Community_ListFragment : Fragment(),onBackPressedListener {

    private lateinit var cl_adapter : Cl_Adapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_community__list, container, false)

        // 뒤로가기 버튼
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)

        var viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ListViewModel_cl::class.java)

        cl_adapter = Cl_Adapter(this)

        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = cl_adapter
        observerData(viewModel)


        return view
    }

    override fun onBackPressed() {
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        fragmentManager?.popBackStack()
    }

    fun observerData(viewModel: ListViewModel_cl){
        viewModel.fetchData_cl().observe(this, Observer {
            cl_adapter.setListData(it)
            cl_adapter.notifyDataSetChanged()
        })
    }

    fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
        requireActivity()
    } catch (e: IllegalStateException) {
        this
    }



}
