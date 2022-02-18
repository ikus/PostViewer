package com.example.postviewer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postviewer.R
import com.example.postviewer.data.model.Comment
import com.example.postviewer.data.model.Post

class CommentAdapter(private val listaDatos: List<Comment>?): RecyclerView.Adapter<CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater =  LayoutInflater.from(parent.context)
        return CommentViewHolder(layoutInflater.inflate(R.layout.comment_list_content,parent,false))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item:Comment
        item = listaDatos?.get(position)!!
        holder.render(item)
    }

    override fun getItemCount(): Int = listaDatos?.size!!

}