package com.vendedor.app.core.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiKeyManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefs: SharedPreferences by lazy {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        EncryptedSharedPreferences.create(
            context,
            "vendedor_api_keys",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    var geminiApiKey: String
        get() = prefs.getString(KEY_GEMINI, "") ?: ""
        set(value) = prefs.edit().putString(KEY_GEMINI, value).apply()

    var ebayClientId: String
        get() = prefs.getString(KEY_EBAY_CLIENT_ID, "") ?: ""
        set(value) = prefs.edit().putString(KEY_EBAY_CLIENT_ID, value).apply()

    var ebayClientSecret: String
        get() = prefs.getString(KEY_EBAY_SECRET, "") ?: ""
        set(value) = prefs.edit().putString(KEY_EBAY_SECRET, value).apply()

    // Sender address (saved once, reused for shipping)
    var senderName: String
        get() = prefs.getString(KEY_SENDER_NAME, "") ?: ""
        set(value) = prefs.edit().putString(KEY_SENDER_NAME, value).apply()

    var senderAddress: String
        get() = prefs.getString(KEY_SENDER_ADDRESS, "") ?: ""
        set(value) = prefs.edit().putString(KEY_SENDER_ADDRESS, value).apply()

    var senderCity: String
        get() = prefs.getString(KEY_SENDER_CITY, "") ?: ""
        set(value) = prefs.edit().putString(KEY_SENDER_CITY, value).apply()

    var senderState: String
        get() = prefs.getString(KEY_SENDER_STATE, "") ?: ""
        set(value) = prefs.edit().putString(KEY_SENDER_STATE, value).apply()

    var senderZip: String
        get() = prefs.getString(KEY_SENDER_ZIP, "") ?: ""
        set(value) = prefs.edit().putString(KEY_SENDER_ZIP, value).apply()

    val hasGeminiKey: Boolean get() = geminiApiKey.isNotBlank()
    val hasEbayKeys: Boolean get() = ebayClientId.isNotBlank() && ebayClientSecret.isNotBlank()

    companion object {
        private const val KEY_GEMINI = "gemini_api_key"
        private const val KEY_EBAY_CLIENT_ID = "ebay_client_id"
        private const val KEY_EBAY_SECRET = "ebay_client_secret"
        private const val KEY_SENDER_NAME = "sender_name"
        private const val KEY_SENDER_ADDRESS = "sender_address"
        private const val KEY_SENDER_CITY = "sender_city"
        private const val KEY_SENDER_STATE = "sender_state"
        private const val KEY_SENDER_ZIP = "sender_zip"
    }
}
