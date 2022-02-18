package com.example.postviewer.data.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "comment")
data class Comment(

    @PrimaryKey
    @SerializedName("id")
    var id: Int? = null,

    @ColumnInfo(name = "postId")
    @SerializedName("postId")
    var postId: Int? = null,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String? = null,

    @ColumnInfo(name = "email")
    @SerializedName("email")
    var email: String? = null,

    @ColumnInfo(name = "body")
    @SerializedName("body")
    var body: String? = null

)
