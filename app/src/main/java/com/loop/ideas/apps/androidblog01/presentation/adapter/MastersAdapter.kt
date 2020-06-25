package com.loop.ideas.apps.androidblog01.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.loop.ideas.apps.androidblog01.BR
import com.loop.ideas.apps.androidblog01.R
import com.loop.ideas.apps.androidblog01.databinding.ItemMasterBinding
import com.loop.ideas.apps.androidblog01.presentation.model.UserPresentation

/*
 * Created by Christopher Elias on 24/06/2020.
 * christopher.elias@loop-ideas.com
 * 
 * Loop Ideas
 * Lima, Peru.
 */
class MastersAdapter(private var masters: List<UserPresentation>,
                     val listener: (model: UserPresentation) -> Unit) : RecyclerView.Adapter<MastersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MastersViewHolder {
        return MastersViewHolder(DataBindingUtil.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_master, parent, false))!!)
    }

    override fun getItemCount(): Int = masters.size

    override fun onBindViewHolder(holder: MastersViewHolder, position: Int) {
        val master =  masters[position]
        holder.binding.setVariable(BR.user, master)
        holder.binding.executePendingBindings()
        holder.binding.root.setOnClickListener {
            listener(master)
        }
    }

    fun addItems(newItems: List<UserPresentation>) {
        masters = newItems
        notifyDataSetChanged()
    }
}

class MastersViewHolder(val binding: ItemMasterBinding) : RecyclerView.ViewHolder(binding.root)