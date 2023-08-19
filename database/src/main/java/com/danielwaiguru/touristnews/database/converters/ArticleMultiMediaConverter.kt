package com.danielwaiguru.touristnews.database.converters

import androidx.room.TypeConverter
import com.danielwaiguru.touristnews.database.entities.ArticleEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ArticleMultiMediaConverter {
    private val gson: Gson by lazy { Gson() }
    private val type: Type by lazy {
        object : TypeToken<List<ArticleEntity.MultiMediaEntity>>() {}.type
    }

    @TypeConverter
    fun fromListToString(medias: List<ArticleEntity.MultiMediaEntity>?): String? {
        return gson.toJson(medias, type)
    }

    @TypeConverter
    fun fromStringToListS(jsonString: String?): List<ArticleEntity.MultiMediaEntity>? {
        return gson.fromJson(jsonString, type)
    }
}