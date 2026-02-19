package com.vendedor.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "listings",
    foreignKeys = [ForeignKey(
        entity = ItemEntity::class,
        parentColumns = ["id"],
        childColumns = ["itemId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("itemId")]
)
data class ListingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val itemId: Long,
    val marketplace: String,
    val externalListingId: String? = null,
    val listingUrl: String? = null,
    val status: String = "draft",
    val listedPrice: Double? = null,
    val listedAt: Long? = null,
    val updatedAt: Long = System.currentTimeMillis()
)
