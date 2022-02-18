package com.example.postviewer.di


import android.content.Context
import androidx.room.Room
import com.example.postviewer.data.database.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val POST_DATABASE_NAME = "post_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PostDatabase::class.java, POST_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideMovieDao(db: PostDatabase) = db.getPostDao()
}