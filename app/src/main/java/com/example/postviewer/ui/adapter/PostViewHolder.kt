package com.example.postviewer.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.postviewer.data.model.Post
import com.example.postviewer.databinding.ItemListContentBinding


class PostViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = ItemListContentBinding.bind(view)

    //val idView: TextView = binding.idText
    val contentView: TextView = binding.content


    fun render(post: Post, onClickListener: (Post) -> Unit){
        //idView.text = post.id.toString()
        contentView.text = post.title
        itemView.setOnClickListener{ onClickListener(post) }
    }


}