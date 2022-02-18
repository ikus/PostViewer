package com.example.postviewer.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.postviewer.data.model.Post
import com.example.postviewer.databinding.ItemListContentBinding


class PostViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = ItemListContentBinding.bind(view)
    val bodyView: TextView = binding.body
    val contentView: TextView = binding.content

    fun render(post: Post, onClickListener: (Post) -> Unit){
        contentView.text = post.title
        bodyView.text = post.body
        itemView.setOnClickListener{ onClickListener(post) }
    }
}