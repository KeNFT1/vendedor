package com.vendedor.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "price_research",
    foreignKeys = [ForeignKey(
        entity = ItemEntity::class,
        parentColumns = ["id"],
        childColumns = ["itemId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("itemId")]
)
data class PriceResearchEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val itemId: Long,
    val source: String,
    val query: String,
    val averagePrice: Double? = null,
    val medianPrice: Double? = null,
    val lowPrice: Double? = null,
    val highPrice: Double? = null,
    val sampleSize: Int = 0,
    val resultsJson: String? = null,
    val researchedAt: Long = System.currentTimeMillis()
)
