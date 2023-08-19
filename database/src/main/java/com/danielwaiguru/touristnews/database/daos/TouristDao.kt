package com.danielwaiguru.touristnews.database.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.danielwaiguru.touristnews.database.entities.TouristEntity
import com.danielwaiguru.touristnews.database.entities.TouristEntity.Companion.TOURISTS_TABLE_NAME

/**
 * Data access object for [TouristEntity]
 */
@Dao
interface TouristDao {
    /**
     * Save tourists
     *
     * @param tourists
     */
    @Upsert
    suspend fun saveTourists(tourists: List<TouristEntity>)

    /**
     * Get cached tourists
     *
     * @return
     */
    @Query("SELECT * FROM $TOURISTS_TABLE_NAME ORDER BY id DESC")
    fun getCachedTourists(): PagingSource<Int, TouristEntity>

    /**
     * Clear all [TouristEntity]
     *
     */
    @Query("DELETE FROM $TOURISTS_TABLE_NAME")
    suspend fun clearAll()

    @Query("SELECT Max(nextPage) FROM $TOURISTS_TABLE_NAME")
    fun getNextPage(): Int?
}