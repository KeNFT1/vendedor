package com.vendedor.app.feature.identification.data

import android.graphics.BitmapFactory
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.squareup.moshi.Moshi
import com.vendedor.app.core.data.preferences.ApiKeyManager
import com.vendedor.app.feature.identification.data.model.AiIdentificationResponse
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiVisionService @Inject constructor(
    private val apiKeyManager: ApiKeyManager,
    private val moshi: Moshi
) {
    private fun createModel(): GenerativeModel {
        val key = apiKeyManager.geminiApiKey
        require(key.isNotBlank()) { "Gemini API key not configured. Go to Settings to add it." }
        return GenerativeModel(modelName = "gemini-2.0-flash", apiKey = key)
    }

    suspend fun identifyItem(photoPath: String): AiIdentificationResponse {
        val bitmap = BitmapFactory.decodeFile(photoPath)
            ?: throw IllegalArgumentException("Cannot decode image: $photoPath")

        val model = createModel()
        val response = model.generateContent(
            content {
                image(bitmap)
                text(IDENTIFICATION_PROMPT)
            }
        )

        val responseText = response.text
            ?: throw IllegalStateException("Empty response from Gemini")

        // Extract JSON from response (may be wrapped in markdown code block)
        val jsonString = extractJson(responseText)

        val adapter = moshi.adapter(AiIdentificationResponse::class.java)
        return adapter.fromJson(jsonString)
            ?: throw IllegalStateException("Failed to parse identification response")
    }

    private fun extractJson(text: String): String {
        // Try to extract JSON from markdown code block
        val codeBlockRegex = Regex("```(?:json)?\\s*\\n?(\\{.*?})\\s*\\n?```", RegexOption.DOT_MATCHES_ALL)
        val match = codeBlockRegex.find(text)
        if (match != null) {
            return match.groupValues[1]
        }

        // Try to find raw JSON object
        val jsonStart = text.indexOf('{')
        val jsonEnd = text.lastIndexOf('}')
        if (jsonStart != -1 && jsonEnd != -1 && jsonEnd > jsonStart) {
            return text.substring(jsonStart, jsonEnd + 1)
        }

        return text
    }

    companion object {
        private val IDENTIFICATION_PROMPT = """
You are a product identification assistant for a resale marketplace app.
Analyze the provided photo of an item and return ONLY a JSON object (no other text) with these fields:

{
  "title": "concise product title for a marketplace listing (max 80 chars)",
  "category": "product category (e.g., Electronics, Clothing, Books, Home & Garden, Toys, Sports, Collectibles)",
  "subcategory": "more specific category",
  "brand": "brand name if identifiable, null otherwise",
  "condition": "one of: new, like_new, good, fair, poor (estimate from visual appearance)",
  "description": "2-3 sentence marketplace listing description highlighting key features and condition",
  "estimatedLengthInches": estimated length in inches as a number or null,
  "estimatedWidthInches": estimated width in inches as a number or null,
  "estimatedHeightInches": estimated height in inches as a number or null,
  "estimatedWeightOz": estimated weight in ounces as a number or null,
  "suggestedSearchTerms": ["term1", "term2", "term3"] for price research
}

Important:
- Be specific in the title (include brand, model, color when visible)
- Description should be written as if for an eBay or Mercari listing
- Estimate dimensions and weight based on visual cues and known product sizes
- Search terms should be effective for finding comparable sold items on eBay
- Return ONLY the JSON object, no explanation
        """.trimIndent()
    }
}
