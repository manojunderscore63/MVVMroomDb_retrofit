package com.app.mvvmroomdb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.mvvmroomdb.R
import com.app.mvvmroomdb.adapter.recAdapter
import com.app.mvvmroomdb.viewmodel.UserViewModel
import com.app.mvvmroomdb.databinding.FragmentPage1Binding
import com.app.mvvmroomdb.showToast

class Page1 : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var adapter: recAdapter
    private lateinit var binding: FragmentPage1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPage1Binding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.floatingActionButton.setOnClickListener{
            Navigation.findNavController(binding.root).navigate(R.id.action_page1_to_page2)
        }

        binding.floatingActionButton2.setOnClickListener{
            Navigation.findNavController(binding.root).navigate(R.id.action_page1_to_page3)
        }

        binding.deleteall.setOnClickListener{
            deleteAlldata()
        }

        setAdapter()

        return binding.root
    }

    private fun deleteAlldata() {
        mUserViewModel.deleteAllData()
        showToast("Data Deleted.")
    }

    private fun setAdapter() {
        adapter = recAdapter(requireContext())
        binding.rec.setHasFixedSize(true)
        binding.rec.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rec.adapter = adapter

        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })
    }

}