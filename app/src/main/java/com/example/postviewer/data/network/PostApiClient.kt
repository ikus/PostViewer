package com.example.postviewer.data.network

import com.example.postviewer.data.model.Comment
import com.example.postviewer.data.model.Post
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PostApiClient {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts")
    suspend fun getPosts(@Query("id") id:Int): List<Post>

    @GET("comments")
    suspend fun getComments(): List<Comment>

    @GET("comments")
    suspend fun getComments(@Query("postId") postId:Int): List<Comment>
}