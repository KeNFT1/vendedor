package com.vendedor.app.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vendedor.app.core.data.local.entity.ListingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ListingDao {
    @Query("SELECT * FROM listings WHERE itemId = :itemId")
    fun getListingsForItem(itemId: Long): Flow<List<ListingEntity>>

    @Query("SELECT * FROM listings WHERE id = :id")
    suspend fun getListingById(id: Long): ListingEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listing: ListingEntity): Long

    @Update
    suspend fun update(listing: ListingEntity)

    @Delete
    suspend fun delete(listing: ListingEntity)
}
