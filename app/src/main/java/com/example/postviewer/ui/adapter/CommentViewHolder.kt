package com.example.postviewer.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.postviewer.data.model.Comment
import com.example.postviewer.databinding.CommentListContentBinding

class CommentViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding =   CommentListContentBinding.bind(view)
    val nameView: TextView = binding.commentName
    val bodyView: TextView = binding.commentBody
    val emailView: TextView = binding.commentEmail

    fun render(comment: Comment){
        nameView.text = comment.name
        bodyView.text = comment.body
        emailView.text = comment.email
    }
}