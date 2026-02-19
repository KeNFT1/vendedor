package com.vendedor.app.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vendedor.app.core.data.local.entity.PriceResearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PriceResearchDao {
    @Query("SELECT * FROM price_research WHERE itemId = :itemId ORDER BY researchedAt DESC")
    fun getResearchForItem(itemId: Long): Flow<List<PriceResearchEntity>>

    @Query("SELECT * FROM price_research WHERE itemId = :itemId ORDER BY researchedAt DESC LIMIT 1")
    suspend fun getLatestResearch(itemId: Long): PriceResearchEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(research: PriceResearchEntity): Long

    @Query("DELETE FROM price_research WHERE itemId = :itemId")
    suspend fun deleteAllForItem(itemId: Long)
}
