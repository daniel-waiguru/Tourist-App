package com.danielwaiguru.touristnews.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danielwaiguru.touristnews.database.entities.TouristEntity.Companion.TOURISTS_TABLE_NAME

@Entity(tableName = TOURISTS_TABLE_NAME)
data class TouristEntity(
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "tourist_email")
    val touristEmail: String,
    @ColumnInfo(name = "tourist_location")
    val touristLocation: String,
    @ColumnInfo(name = "tourist_name")
    val touristName: String
) {
    companion object {
        const val TOURISTS_TABLE_NAME = "tb_tourists"
    }
}