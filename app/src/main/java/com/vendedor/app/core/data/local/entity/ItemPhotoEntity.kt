package com.vendedor.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "item_photos",
    foreignKeys = [ForeignKey(
        entity = ItemEntity::class,
        parentColumns = ["id"],
        childColumns = ["itemId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("itemId")]
)
data class ItemPhotoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val itemId: Long,
    val filePath: String,
    val isPrimary: Boolean = false,
    val sortOrder: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
