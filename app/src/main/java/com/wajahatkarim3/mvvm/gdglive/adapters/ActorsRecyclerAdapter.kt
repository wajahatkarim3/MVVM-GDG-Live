package com.wajahatkarim3.mvvm.gdglive.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.wajahatkarim3.mvvm.gdglive.R
import com.wajahatkarim3.mvvm.gdglive.app.Constants
import com.wajahatkarim3.mvvm.gdglive.databinding.ActorItemLayoutBinding
import com.wajahatkarim3.mvvm.gdglive.model.ActorModel

class ActorsRecyclerAdapter : ListAdapter<ActorModel, ActorsRecyclerAdapter.ActorViewHolder>(ACTOR_DIFF_CALLBACK)
{
    private val actorsList = arrayListOf<ActorModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(ActorItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return actorsList.size
    }

    override fun getItem(position: Int): ActorModel {
        return actorsList[position]
    }

    fun clearItems()
    {
        actorsList.clear()
    }

    fun setItems(list: List<ActorModel>)
    {
        clearItems()
        actorsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ActorViewHolder(val itemBinding: ActorItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root)
    {
        fun bind(actor: ActorModel)
        {
            itemBinding.imgActor.load(Constants.IMAGE_URL + actor.profile) {
                error(R.color.colorPrimary)
                placeholder(R.color.colorAccent)
                crossfade(true)
                transformations(CircleCropTransformation())
            }

            itemBinding.txtActorName.text = actor.name
            itemBinding.txtActorRole.text = actor.character
        }
    }

    companion object {
        private val ACTOR_DIFF_CALLBACK = object : DiffUtil.ItemCallback<ActorModel>() {
            override fun areItemsTheSame(oldItem: ActorModel, newItem: ActorModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ActorModel, newItem: ActorModel): Boolean {
                return oldItem.id == newItem.id && oldItem.name == newItem.name
            }
        }
    }
}