package com.example.postviewer.data.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "post")
data class Post(

    @PrimaryKey
    @SerializedName("id")
    var id: Int? = null,

    @ColumnInfo(name = "userId")
    @SerializedName("userId")
    var userId: Int? = null,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String? = null,

    @ColumnInfo(name = "body")
    @SerializedName("body")
    var body: String? = null
)