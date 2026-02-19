package com.vendedor.app.feature.settings.viewmodel

import androidx.lifecycle.ViewModel
import com.vendedor.app.core.data.preferences.ApiKeyManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val apiKeyManager: ApiKeyManager
) : ViewModel() {

    fun getGeminiKey(): String = apiKeyManager.geminiApiKey
    fun getEbayClientId(): String = apiKeyManager.ebayClientId
    fun getEbaySecret(): String = apiKeyManager.ebayClientSecret

    fun getSenderName(): String = apiKeyManager.senderName
    fun getSenderAddress(): String = apiKeyManager.senderAddress
    fun getSenderCity(): String = apiKeyManager.senderCity
    fun getSenderState(): String = apiKeyManager.senderState
    fun getSenderZip(): String = apiKeyManager.senderZip

    fun saveApiKeys(geminiKey: String, ebayClientId: String, ebaySecret: String) {
        apiKeyManager.geminiApiKey = geminiKey
        apiKeyManager.ebayClientId = ebayClientId
        apiKeyManager.ebayClientSecret = ebaySecret
    }

    fun saveSenderAddress(name: String, address: String, city: String, state: String, zip: String) {
        apiKeyManager.senderName = name
        apiKeyManager.senderAddress = address
        apiKeyManager.senderCity = city
        apiKeyManager.senderState = state
        apiKeyManager.senderZip = zip
    }
}
