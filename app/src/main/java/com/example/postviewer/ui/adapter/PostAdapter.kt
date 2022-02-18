package com.example.postviewer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postviewer.R
import com.example.postviewer.data.model.Post


class PostAdapter(private val listaDatos: List<Post>?, private val onClickListener:(Post) -> Unit): RecyclerView.Adapter<PostViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater =  LayoutInflater.from(parent.context)
        return PostViewHolder(layoutInflater.inflate(R.layout.item_list_content,parent,false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item:Post
        item = listaDatos?.get(position)!!
        holder.render(item,onClickListener)
    }

    override fun getItemCount(): Int = listaDatos?.size!!

}