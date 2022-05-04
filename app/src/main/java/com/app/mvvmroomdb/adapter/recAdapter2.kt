package com.app.mvvmroomdb.adapter

import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.mvvmroomdb.R
import com.app.mvvmroomdb.data.MemeData
import com.app.mvvmroomdb.data.User
import com.app.mvvmroomdb.databinding.ItemTypeBinding
import com.app.mvvmroomdb.databinding.MemesTypeBinding

class recAdapter2 (var context: Context) : RecyclerView.Adapter<recAdapter2.viewh>() {
    private var list = arrayListOf<MemeData>()
    public class viewh(val binding: MemesTypeBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewh {
        val v: MemesTypeBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), R.layout.memes_type,
            parent, false)

        return viewh(v)
    }

    override fun onBindViewHolder(holder: viewh, position: Int) {
        holder.binding.meme = list.get(position)

        holder.binding.memeImage.post {
            val wid = holder.binding.memeImage.measuredWidth
            val height = holder.binding.memeImage.measuredHeight

            val animator = ValueAnimator.ofInt(height, wid)

            val params = holder.binding.memeImage.layoutParams

            animator.addUpdateListener ( object : ValueAnimator.AnimatorUpdateListener{
                override fun onAnimationUpdate(p0: ValueAnimator?) {
                    params.height = p0?.getAnimatedValue() as Int
                    holder.binding.memeImage.layoutParams = params
                }
            })

            animator.setDuration(2000)
            animator.interpolator = DecelerateInterpolator()
            animator.start()

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(newl: ArrayList<MemeData>){
        this.list = newl
        notifyDataSetChanged()
    }
}