package com.example.postviewer

import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.postviewer.data.PostRepository
import com.example.postviewer.data.model.Comment
import com.example.postviewer.data.model.Post
import com.example.postviewer.placeholder.PlaceholderContent
import com.example.postviewer.databinding.FragmentItemDetailBinding
import com.example.postviewer.ui.adapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ItemDetailFragment : Fragment() {

    @Inject
    internal lateinit var repository: PostRepository


    private var itemId:Int? = null
    private var item: PlaceholderContent.PlaceholderItem? = null

    lateinit var itemDetailTextView: TextView
    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val dragListener = View.OnDragListener { v, event ->
        if (event.action == DragEvent.ACTION_DROP) {
            val clipDataItem: ClipData.Item = event.clipData.getItemAt(0)
            val dragData = clipDataItem.text
            item = PlaceholderContent.ITEM_MAP[dragData]
            updateContent()
        }
        true
    }

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
        itemDetailTextView = binding.itemDetail
        updateContent()
        rootView.setOnDragListener(dragListener)
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
            if(comments==null){
                    comments = post?.id?.let { repository.getCommentsFromApi(it) }
            }
            activity?.runOnUiThread{
                toolbarLayout?.title = post?.title
                itemDetailTextView.text = post?.body
            }
            //TODO: Mostrar comentarios
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