package com.app.mvvmroomdb.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.app.mvvmroomdb.data.User
import com.app.mvvmroomdb.viewmodel.UserViewModel
import com.app.mvvmroomdb.databinding.FragmentPage2Binding
import com.app.mvvmroomdb.showToast

class Page2 : Fragment() {
    private lateinit var binding: FragmentPage2Binding
    private lateinit var mUserViewModel: UserViewModel
    private var currentUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPage2Binding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        getData()
        setViews()

        clicks()

        return binding.root
    }

    private fun clicks() {
        binding.return2.setOnClickListener{
            Navigation.findNavController(binding.root).navigateUp()
        }

        binding.done.setOnClickListener{
            adddata()
        }

        binding.delete.setOnClickListener{
            deleteData()
        }
    }

    private fun setViews() {
        if (currentUser!=null){
            binding.done.text = "Update"
            binding.delete.visibility = View.VISIBLE
        }else{
            binding.done.text = "Add"
            binding.delete.visibility = View.GONE
        }
    }

    private fun getData() {
        if (arguments!=null){
            currentUser = arguments?.getParcelable<User>("class")!!
            binding.firstname.setText(currentUser?.firstName)
            binding.lastname.setText(currentUser?.lastName)
            binding.age.setText(currentUser?.age.toString())
        }
    }

    private fun adddata() {
        val firstname = binding.firstname.text.toString()
        val lastname = binding.lastname.text.toString()
        val age = binding.age.text

        if (inputcheck(firstname, lastname, age)){
            if (currentUser!=null){
                val user = User(currentUser?.id!!, firstname, lastname, Integer.parseInt(age.toString()))
                mUserViewModel.updateUser(user)
                showToast("Data Updated.")
            }else{
                val user = User(0, firstname, lastname, Integer.parseInt(age.toString()))
                mUserViewModel.addUser(user)
                showToast("Data Added.")
            }

            resetinputs()
            binding.return2.performClick()
        }

    }

    private fun deleteData() {
        mUserViewModel.deleteUser(currentUser)
        showToast("Data Deleted.")

        resetinputs()
        binding.return2.performClick()
    }

    private fun resetinputs() {
        binding.firstname.text = null
        binding.lastname.text = null
        binding.age.text = null
    }

    private fun inputcheck(firstname: String, lastname: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || age.isEmpty())
    }


}