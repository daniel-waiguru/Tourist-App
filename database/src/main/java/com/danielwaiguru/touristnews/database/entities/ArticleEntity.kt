package com.danielwaiguru.touristnews.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.danielwaiguru.touristnews.database.converters.ArticleMultiMediaConverter
import com.danielwaiguru.touristnews.database.entities.ArticleEntity.Companion.ARTICLES_TABLED_NAME

@Entity(tableName = ARTICLES_TABLED_NAME)
@TypeConverters(ArticleMultiMediaConverter::class)
data class ArticleEntity(
    @ColumnInfo(name = "comment_count")
    val commentCount: Int,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    val description: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val location: String,
    @ColumnInfo("multi_media")
    val multiMedia: List<MultiMediaEntity>,
    val title: String,
    @Embedded
    val user: UserEntity
) {
    data class MultiMediaEntity(
        @ColumnInfo(name = "created_at")
        val createdAt: String,
        val description: String?,
        val id: Int,
        val name: String,
        val title: String?,
        val url: String
    )
    data class UserEntity(
        val name: String,
        @ColumnInfo(name = "profile_picture")
        val profilePicture: String,
        @ColumnInfo(name = "user_id")
        val userId: Int
    )
    companion object {
        const val ARTICLES_TABLED_NAME = "articles_tb"
    }
}
