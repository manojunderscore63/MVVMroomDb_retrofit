package com.app.mvvmroomdb

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(url: String){
//    Log.w("valuelist", url)
    Glide.with(this).load(url).into(this)
}