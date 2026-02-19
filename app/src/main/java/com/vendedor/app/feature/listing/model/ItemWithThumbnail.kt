package com.vendedor.app.feature.listing.model

data class ItemWithThumbnail(
    val id: Long,
    val title: String,
    val suggestedPriceLow: Double?,
    val suggestedPriceHigh: Double?,
    val askingPrice: Double?,
    val status: String,
    val createdAt: Long,
    val primaryPhotoPath: String?
)
