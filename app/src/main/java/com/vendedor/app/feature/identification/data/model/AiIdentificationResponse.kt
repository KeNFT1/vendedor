package com.vendedor.app.feature.identification.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AiIdentificationResponse(
    val title: String = "",
    val category: String? = null,
    val subcategory: String? = null,
    val brand: String? = null,
    val condition: String? = null,
    val description: String = "",
    val estimatedLengthInches: Float? = null,
    val estimatedWidthInches: Float? = null,
    val estimatedHeightInches: Float? = null,
    val estimatedWeightOz: Float? = null,
    val estimatedPriceLow: Double? = null,
    val estimatedPriceHigh: Double? = null,
    val suggestedSearchTerms: List<String> = emptyList()
)
