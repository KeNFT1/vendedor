package com.vendedor.app.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vendedor.app.core.data.local.entity.ItemPhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemPhotoDao {
    @Query("SELECT * FROM item_photos WHERE itemId = :itemId ORDER BY sortOrder ASC")
    fun getPhotosForItem(itemId: Long): Flow<List<ItemPhotoEntity>>

    @Query("SELECT * FROM item_photos WHERE itemId = :itemId AND isPrimary = 1 LIMIT 1")
    suspend fun getPrimaryPhoto(itemId: Long): ItemPhotoEntity?

    @Query("SELECT * FROM item_photos WHERE isPrimary = 1")
    fun getAllPrimaryPhotos(): Flow<List<ItemPhotoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: ItemPhotoEntity): Long

    @Update
    suspend fun update(photo: ItemPhotoEntity)

    @Delete
    suspend fun delete(photo: ItemPhotoEntity)

    @Query("DELETE FROM item_photos WHERE itemId = :itemId")
    suspend fun deleteAllForItem(itemId: Long)

    @Query("UPDATE item_photos SET isPrimary = 0 WHERE itemId = :itemId")
    suspend fun clearPrimaryForItem(itemId: Long)

    @Query("UPDATE item_photos SET isPrimary = 1 WHERE id = :photoId")
    suspend fun setPrimary(photoId: Long)
}
