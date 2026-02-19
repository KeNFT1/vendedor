package com.vendedor.app.feature.shipping.viewmodel

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vendedor.app.core.data.local.dao.ItemDao
import com.vendedor.app.core.data.local.dao.ShippingInfoDao
import com.vendedor.app.core.data.local.entity.ShippingInfoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ShippingUiState(
    val itemId: Long = 0,
    val itemTitle: String = "",
    // Package info (from item)
    val lengthInches: String = "",
    val widthInches: String = "",
    val heightInches: String = "",
    val weightOz: String = "",
    // Ship-to address
    val shipToName: String = "",
    val shipToAddress: String = "",
    val shipToCity: String = "",
    val shipToState: String = "",
    val shipToZip: String = "",
    // Tracking
    val trackingNumber: String = "",
    val isSaving: Boolean = false,
    val copiedField: String? = null
)

@HiltViewModel
class ShippingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val itemDao: ItemDao,
    private val shippingInfoDao: ShippingInfoDao,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val itemId: Long = savedStateHandle["itemId"] ?: 0L

    private val _uiState = MutableStateFlow(ShippingUiState(itemId = itemId))
    val uiState: StateFlow<ShippingUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val item = itemDao.getItemById(itemId) ?: return@launch
            val shippingInfo = shippingInfoDao.getById(itemId)

            _uiState.update {
                it.copy(
                    itemTitle = item.title,
                    lengthInches = item.estimatedLengthInches?.toString() ?: "",
                    widthInches = item.estimatedWidthInches?.toString() ?: "",
                    heightInches = item.estimatedHeightInches?.toString() ?: "",
                    weightOz = item.estimatedWeightOz?.toString() ?: "",
                    shipToName = shippingInfo?.shipToName ?: "",
                    shipToAddress = shippingInfo?.shipToAddress ?: "",
                    shipToCity = shippingInfo?.shipToCity ?: "",
                    shipToState = shippingInfo?.shipToState ?: "",
                    shipToZip = shippingInfo?.shipToZip ?: "",
                    trackingNumber = shippingInfo?.trackingNumber ?: ""
                )
            }
        }
    }

    fun updateShipToName(v: String) { _uiState.update { it.copy(shipToName = v) } }
    fun updateShipToAddress(v: String) { _uiState.update { it.copy(shipToAddress = v) } }
    fun updateShipToCity(v: String) { _uiState.update { it.copy(shipToCity = v) } }
    fun updateShipToState(v: String) { _uiState.update { it.copy(shipToState = v) } }
    fun updateShipToZip(v: String) { _uiState.update { it.copy(shipToZip = v) } }
    fun updateTrackingNumber(v: String) { _uiState.update { it.copy(trackingNumber = v) } }
    fun updateLength(v: String) { _uiState.update { it.copy(lengthInches = v) } }
    fun updateWidth(v: String) { _uiState.update { it.copy(widthInches = v) } }
    fun updateHeight(v: String) { _uiState.update { it.copy(heightInches = v) } }
    fun updateWeight(v: String) { _uiState.update { it.copy(weightOz = v) } }

    fun saveShippingInfo() {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            val state = _uiState.value
            shippingInfoDao.insert(
                ShippingInfoEntity(
                    itemId = itemId,
                    shipToName = state.shipToName.ifBlank { null },
                    shipToAddress = state.shipToAddress.ifBlank { null },
                    shipToCity = state.shipToCity.ifBlank { null },
                    shipToState = state.shipToState.ifBlank { null },
                    shipToZip = state.shipToZip.ifBlank { null },
                    trackingNumber = state.trackingNumber.ifBlank { null }
                )
            )
            _uiState.update { it.copy(isSaving = false) }
        }
    }

    fun copyToClipboard(label: String, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText(label, text))
        _uiState.update { it.copy(copiedField = label) }
    }

    fun copyDimensions() {
        val state = _uiState.value
        copyToClipboard(
            "dimensions",
            "${state.lengthInches} x ${state.widthInches} x ${state.heightInches}"
        )
    }

    fun copyWeight() {
        copyToClipboard("weight", _uiState.value.weightOz)
    }

    fun copyAddress() {
        val state = _uiState.value
        val address = buildString {
            if (state.shipToName.isNotBlank()) appendLine(state.shipToName)
            if (state.shipToAddress.isNotBlank()) appendLine(state.shipToAddress)
            val cityState = listOfNotNull(
                state.shipToCity.ifBlank { null },
                state.shipToState.ifBlank { null }
            ).joinToString(", ")
            if (cityState.isNotBlank()) append(cityState)
            if (state.shipToZip.isNotBlank()) append(" ${state.shipToZip}")
        }
        copyToClipboard("address", address)
    }

    fun openPirateship() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ship.pirateship.com/ship"))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun clearCopiedField() {
        _uiState.update { it.copy(copiedField = null) }
    }
}
