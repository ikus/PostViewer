package com.example.postviewer.data

import com.example.postviewer.data.database.dao.PostDao
import com.example.postviewer.data.model.Comment
import com.example.postviewer.data.model.Post
import com.example.postviewer.data.network.PostService
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api: PostService,
    private val postDao: PostDao
){

    suspend fun getPostsFromApi(): List<Post> {
        val response = api.getPosts()
        return response
    }

    suspend fun getPostsFromApi(id:Int): List<Post> {
        val response = api.getPosts(id)
        return response
    }

    suspend fun getCommentsFromApi(): List<Comment> {
        val response = api.getComments()
        return response
    }

    suspend fun getCommentsFromApi(postId:Int): List<Comment> {
        val response = api.getComments(postId)
        return response
    }


    suspend fun getPostsFromDb(): List<Post> {
        val response = postDao.getPosts()
        return response
    }

    suspend fun getPostsFromDb(id:Int): List<Post> {
        val response = postDao.getPosts(id)
        return response
    }

    suspend fun getCommentsFromDb(): List<Comment> {
        val response = postDao.getComments()
        return response
    }

    suspend fun getCommentsFromDb(postId:Int): List<Comment> {
        val response = postDao.getComments(postId)
        return response
    }

    suspend fun insertPosts(posts:List<Post>){
        postDao.insertAll(posts)
    }

    suspend fun insertComments(comments:List<Comment>){
        postDao.insertComments(comments)
    }

}