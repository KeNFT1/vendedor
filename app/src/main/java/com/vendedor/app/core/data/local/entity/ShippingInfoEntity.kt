package com.vendedor.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "shipping_info",
    foreignKeys = [ForeignKey(
        entity = ItemEntity::class,
        parentColumns = ["id"],
        childColumns = ["itemId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("itemId")]
)
data class ShippingInfoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val itemId: Long,
    val shipToName: String? = null,
    val shipToAddress: String? = null,
    val shipToCity: String? = null,
    val shipToState: String? = null,
    val shipToZip: String? = null,
    val trackingNumber: String? = null,
    val shippedAt: Long? = null,
    val createdAt: Long = System.currentTimeMillis()
)
