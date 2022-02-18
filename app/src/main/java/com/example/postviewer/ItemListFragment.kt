package com.example.postviewer

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.postviewer.PostViewerApp.Companion.prefs
import com.example.postviewer.data.PostRepository
import com.example.postviewer.data.model.Post
import com.example.postviewer.data.network.PostApiClient
import com.example.postviewer.placeholder.PlaceholderContent;
import com.example.postviewer.databinding.FragmentItemListBinding
import com.example.postviewer.databinding.ItemListContentBinding
import com.example.postviewer.ui.adapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ItemListFragment : Fragment() {

    @Inject
    internal lateinit var repository: PostRepository

    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var listPosts:List<Post> = emptyList<Post>()

    private var _binding: FragmentItemListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.itemList
        setupRecyclerView(recyclerView)
    }

    fun onItemSelected(post:  Post){
        val bundle = Bundle()
        bundle.putInt(
            ItemDetailFragment.ARG_ITEM_ID,
            post?.id!!
        )
        findNavController().navigate(R.id.show_item_detail, bundle)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        var result:List<Post>
        var dbupdated=prefs.getDBUpdated()
        CoroutineScope(Dispatchers.IO).launch {
            if(!dbupdated){
                result =   repository.getPostsFromApi()
                repository.insertPosts(result)
                prefs.saveDBUpdated(true)
            }else{
                result =   repository.getPostsFromDb()
            }
            activity?.runOnUiThread{
                recyclerView.adapter = PostAdapter(result) { post ->
                    onItemSelected(
                        post
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}