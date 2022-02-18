package com.example.postviewer.data.database
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.postviewer.data.database.dao.PostDao
import com.example.postviewer.data.model.Post

@Database(entities = [Post::class], version = 1)
@TypeConverters(Post::class)
abstract class PostDatabase: RoomDatabase() {

    abstract fun getPostDao(): PostDao
}

