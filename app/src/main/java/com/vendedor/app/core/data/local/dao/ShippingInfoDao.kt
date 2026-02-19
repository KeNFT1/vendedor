package com.vendedor.app.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vendedor.app.core.data.local.entity.ShippingInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShippingInfoDao {
    @Query("SELECT * FROM shipping_info WHERE itemId = :itemId ORDER BY createdAt DESC")
    fun getShippingInfoForItem(itemId: Long): Flow<List<ShippingInfoEntity>>

    @Query("SELECT * FROM shipping_info WHERE id = :id")
    suspend fun getById(id: Long): ShippingInfoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(info: ShippingInfoEntity): Long

    @Update
    suspend fun update(info: ShippingInfoEntity)

    @Query("DELETE FROM shipping_info WHERE itemId = :itemId")
    suspend fun deleteAllForItem(itemId: Long)
}
