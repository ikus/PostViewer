package com.example.postviewer.data.network

import android.util.Log
import com.example.postviewer.data.model.Comment
import com.example.postviewer.data.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.GET
import javax.inject.Inject

class PostService @Inject constructor(private val api:PostApiClient) {

    suspend fun getPosts(): List<Post>{
        return withContext(Dispatchers.IO) {
            val response = api.getPosts()
            response
        }
    }

    suspend fun getComments(): List<Comment>{
        return withContext(Dispatchers.IO) {
            val response = api.getComments()
            response
        }
    }

}

