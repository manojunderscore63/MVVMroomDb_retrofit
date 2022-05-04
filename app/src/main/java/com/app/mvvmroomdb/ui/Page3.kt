package com.app.mvvmroomdb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.app.mvvmroomdb.adapter.recAdapter2
import com.app.mvvmroomdb.databinding.FragmentPage3Binding
import com.app.mvvmroomdb.viewmodel.RetrofitViewModel

class Page3 : Fragment() {

    private lateinit var binding: FragmentPage3Binding
    private lateinit var mViewmodel: RetrofitViewModel
    private lateinit var adapter: recAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPage3Binding.inflate(inflater, container, false)
        mViewmodel = ViewModelProvider(this).get(RetrofitViewModel::class.java)

        setAapter()

        return binding.root
    }

    private fun setAapter() {
        adapter = recAdapter2(requireContext())
        binding.recRetro.setHasFixedSize(true)
        binding.recRetro.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recRetro.adapter = adapter

        mViewmodel.list_.observe(viewLifecycleOwner) { list2 ->
            adapter.setData(list2)
        }

    }

}