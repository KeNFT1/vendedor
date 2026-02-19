package com.vendedor.app.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val category: String? = null,
    val subcategory: String? = null,
    val brand: String? = null,
    val condition: String? = null,
    val estimatedLengthInches: Float? = null,
    val estimatedWidthInches: Float? = null,
    val estimatedHeightInches: Float? = null,
    val estimatedWeightOz: Float? = null,
    val suggestedPriceLow: Double? = null,
    val suggestedPriceHigh: Double? = null,
    val askingPrice: Double? = null,
    val status: String = "draft",
    val aiIdentificationJson: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
