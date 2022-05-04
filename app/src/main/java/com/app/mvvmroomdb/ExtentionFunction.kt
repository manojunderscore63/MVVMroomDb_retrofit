package com.app.mvvmroomdb

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(text: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(requireActivity(), text, length).show()
}