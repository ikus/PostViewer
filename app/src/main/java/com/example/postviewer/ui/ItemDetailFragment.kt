package com.example.postviewer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postviewer.data.PostRepository
import com.example.postviewer.data.model.Comment
import com.example.postviewer.data.model.Post
import com.example.postviewer.databinding.FragmentItemDetailBinding
import com.example.postviewer.ui.adapter.CommentAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ItemDetailFragment : Fragment() {

    @Inject
    internal lateinit var repository: PostRepository

    private lateinit var recyclerView: RecyclerView
    private var itemId:Int? = null
    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                itemId= it.getInt(ARG_ITEM_ID)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root
        toolbarLayout = binding.toolbarLayout
        recyclerView = binding.commentList!!
        updateContent()
        return rootView
    }

    private fun updateContent() {
        var resultPost:List<Post>
        var resultComment:List<Comment>
        CoroutineScope(Dispatchers.IO).launch {
            var post: Post? = itemId?.let { repository.getPostsFromDb(it).get(0) }
            if(post==null)
                post = itemId?.let { repository.getPostsFromApi(it).get(0) }
            var comments: List<Comment>? = post?.id?.let { repository.getCommentsFromDb(it) }
            if(comments?.size==0){
                comments = post?.id?.let { repository.getCommentsFromApi(it) }
                comments?.let { repository.insertComments(it) }
            }
            activity?.runOnUiThread{
                toolbarLayout?.title = post?.title
                //itemDetailTextView.text = post?.body
                recyclerView?.adapter = CommentAdapter(comments)
            }
        }
    }

    companion object {
                const val ARG_ITEM_ID = "item_id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}