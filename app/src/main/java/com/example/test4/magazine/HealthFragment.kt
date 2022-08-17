package com.example.test4.magazine

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
import com.example.test4.MagazineFragment
import com.example.test4.adapter.firebase.ListViewModel
import com.example.test4.R
import com.example.test4.adapter.Mh_Adapter
import com.example.test4.adapter.firebase.ListViewModel_h
import com.example.test4.onBackPressedListener

class HealthFragment : Fragment(), onBackPressedListener {

    private lateinit var mh_adapter : Mh_Adapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_health, container, false)

        var viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ListViewModel_h::class.java)

        mh_adapter = Mh_Adapter(this)

        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = mh_adapter
        observerData(viewModel)

        return view
    }

    override fun onBackPressed() {
        /*if (this is HomeFragment){
            activity?.finish()
        }*/

        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        fragmentManager?.popBackStack()
    }

    fun observerData(viewModel: ListViewModel_h){
        viewModel.fetchData_h().observe(this, Observer {
            mh_adapter.setListData(it)
            mh_adapter.notifyDataSetChanged()
        })
    }

    fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
        requireActivity()
    } catch (e: IllegalStateException) {
        this
    }
}