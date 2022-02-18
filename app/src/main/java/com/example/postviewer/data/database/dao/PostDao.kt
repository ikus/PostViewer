package com.example.postviewer.data.database.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.postviewer.data.model.Comment
import com.example.postviewer.data.model.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM post")
    suspend fun getPosts():List<Post>

    @Query("SELECT * FROM post WHERE id = :id")
    suspend fun getPosts(id:Int):List<Post>

    @Query("SELECT * FROM comment")
    suspend fun getComments():List<Comment>

    @Query("SELECT * FROM comment WHERE id = :postId")
    suspend fun getComments(postId:Int):List<Comment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(post:List<Post>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post:Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comment:List<Comment>)

    @Query("DELETE FROM post")
    suspend fun deleteAllPosts()
}
