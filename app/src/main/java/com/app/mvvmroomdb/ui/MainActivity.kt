package com.app.mvvmroomdb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.mvvmroomdb.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

//        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

//        binding.lay1.button.setOnClickListener(object: View.OnClickListener{
//            override fun onClick(p0: View?) {
//                addUser()
//            }
//
//        })

//        setAdapter()
    }

//
//
//    private fun addUser() {
//        var user =  User(1, "first name", "last name", 3)
//
//        mUserViewModel.addUser(user)
//
////        CoroutineScope(Dispatchers.IO).launch {
////            UserDatabase.getDatabase(applicationContext).userDao().addUser(user)
////        }
//    }
}