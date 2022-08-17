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
import com.example.test4.adapter.firebase.ListViewModel
import com.example.test4.R
import com.example.test4.adapter.Mk_Adapter
import com.example.test4.adapter.firebase.ListViewModel_k
import com.example.test4.onBackPressedListener

class KnowledgeFragment : Fragment(), onBackPressedListener {

    private lateinit var mk_adapter : Mk_Adapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_knowledge, container, false)

        var viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ListViewModel_k::class.java)

        mk_adapter = Mk_Adapter(this)

        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = mk_adapter
        observerData(viewModel)

        return view
    }

    override fun onBackPressed() {
        fragmentManager?.beginTransaction()?.remove(this)?.commit()
        fragmentManager?.popBackStack()
    }

    fun observerData(viewModel: ListViewModel_k){
        viewModel.fetchData_k().observe(this, Observer {
            mk_adapter.setListData(it)
            mk_adapter.notifyDataSetChanged()
        })
    }

    fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
        requireActivity()
    } catch (e: IllegalStateException) {
        this
    }
}