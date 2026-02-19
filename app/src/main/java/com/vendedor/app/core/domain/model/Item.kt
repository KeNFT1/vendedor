package com.vendedor.app.core.domain.model

data class Item(
    val id: Long = 0,
    val title: String,
    val description: String,
    val category: String? = null,
    val subcategory: String? = null,
    val brand: String? = null,
    val condition: ItemCondition? = null,
    val dimensions: Dimensions? = null,
    val weight: Weight? = null,
    val suggestedPriceLow: Double? = null,
    val suggestedPriceHigh: Double? = null,
    val askingPrice: Double? = null,
    val status: ItemStatus = ItemStatus.DRAFT,
    val photos: List<ItemPhoto> = emptyList(),
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

data class ItemPhoto(
    val id: Long = 0,
    val filePath: String,
    val isPrimary: Boolean = false,
    val sortOrder: Int = 0
)

data class Dimensions(
    val lengthInches: Float,
    val widthInches: Float,
    val heightInches: Float
)

data class Weight(val ounces: Float)

enum class ItemStatus(val value: String) {
    DRAFT("draft"),
    LISTED("listed"),
    SOLD("sold"),
    ARCHIVED("archived");

    companion object {
        fun fromValue(value: String): ItemStatus =
            entries.firstOrNull { it.value == value } ?: DRAFT
    }
}

enum class ItemCondition(val value: String) {
    NEW("new"),
    LIKE_NEW("like_new"),
    GOOD("good"),
    FAIR("fair"),
    POOR("poor");

    companion object {
        fun fromValue(value: String): ItemCondition? =
            entries.firstOrNull { it.value == value }
    }
}
