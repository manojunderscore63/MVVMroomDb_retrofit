package com.app.mvvmroomdb.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.mvvmroomdb.R
import com.app.mvvmroomdb.data.User
import com.app.mvvmroomdb.databinding.FragmentPage1Binding
import com.app.mvvmroomdb.databinding.ItemTypeBinding
import kotlinx.coroutines.NonDisposableHandle
import kotlinx.coroutines.NonDisposableHandle.parent

class recAdapter(var context: Context) : RecyclerView.Adapter<recAdapter.viewh>() {

    private var list = emptyList<User>()
    public class viewh(val binding: ItemTypeBinding):RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewh {
        val v: ItemTypeBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_type,
            parent, false)

        return viewh(v)
    }

    override fun onBindViewHolder(holder: viewh, position: Int) {
        holder.binding.user = list.get(position)

        holder.binding.root.setOnClickListener{
            var bundle = Bundle()
            bundle.putParcelable("class", list.get(position))
            holder.binding.root.findNavController().navigate(R.id.action_page1_to_page2, bundle)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(newl: List<User>){
        this.list = newl
        notifyDataSetChanged()
    }
}