package com.vendedor.app.feature.listing.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.content.Context
import com.vendedor.app.core.data.local.dao.ItemDao
import com.vendedor.app.core.data.local.dao.ItemPhotoDao
import com.vendedor.app.core.data.local.entity.ItemEntity
import com.vendedor.app.core.data.local.entity.ItemPhotoEntity
import com.vendedor.app.core.util.PhotoUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ListingFormState(
    val itemId: Long = 0,
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val brand: String = "",
    val condition: String = "",
    val lengthInches: String = "",
    val widthInches: String = "",
    val heightInches: String = "",
    val weightOz: String = "",
    val askingPrice: String = "",
    val suggestedPriceLow: String = "",
    val suggestedPriceHigh: String = "",
    val status: String = "draft",
    val photos: List<ItemPhotoEntity> = emptyList(),
    val isLoading: Boolean = true,
    val isSaving: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ListingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val itemDao: ItemDao,
    private val itemPhotoDao: ItemPhotoDao,
    @ApplicationContext private val appContext: Context
) : ViewModel() {

    private val itemId: Long = savedStateHandle["itemId"] ?: 0L

    private val _uiState = MutableStateFlow(ListingFormState(itemId = itemId))
    val uiState: StateFlow<ListingFormState> = _uiState.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    init {
        loadItem()
    }

    private fun loadItem() {
        viewModelScope.launch {
            val item = itemDao.getItemById(itemId)
            val photos = itemPhotoDao.getPhotosForItem(itemId).first()

            if (item != null) {
                _uiState.update {
                    it.copy(
                        title = item.title,
                        description = item.description,
                        category = item.category ?: "",
                        brand = item.brand ?: "",
                        condition = item.condition ?: "",
                        lengthInches = item.estimatedLengthInches?.toString() ?: "",
                        widthInches = item.estimatedWidthInches?.toString() ?: "",
                        heightInches = item.estimatedHeightInches?.toString() ?: "",
                        weightOz = item.estimatedWeightOz?.toString() ?: "",
                        askingPrice = item.askingPrice?.toString() ?: "",
                        suggestedPriceLow = item.suggestedPriceLow?.toString() ?: "",
                        suggestedPriceHigh = item.suggestedPriceHigh?.toString() ?: "",
                        status = item.status,
                        photos = photos,
                        isLoading = false
                    )
                }
            } else {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun updateTitle(v: String) { _uiState.update { it.copy(title = v) } }
    fun updateDescription(v: String) { _uiState.update { it.copy(description = v) } }
    fun updateCategory(v: String) { _uiState.update { it.copy(category = v) } }
    fun updateBrand(v: String) { _uiState.update { it.copy(brand = v) } }
    fun updateCondition(v: String) { _uiState.update { it.copy(condition = v) } }
    fun updateLength(v: String) { _uiState.update { it.copy(lengthInches = v) } }
    fun updateWidth(v: String) { _uiState.update { it.copy(widthInches = v) } }
    fun updateHeight(v: String) { _uiState.update { it.copy(heightInches = v) } }
    fun updateWeight(v: String) { _uiState.update { it.copy(weightOz = v) } }
    fun updateAskingPrice(v: String) { _uiState.update { it.copy(askingPrice = v) } }

    fun saveListing(onSaved: () -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            try {
                val state = _uiState.value
                val item = itemDao.getItemById(itemId) ?: return@launch

                itemDao.update(
                    item.copy(
                        title = state.title,
                        description = state.description,
                        category = state.category.ifBlank { null },
                        brand = state.brand.ifBlank { null },
                        condition = state.condition.ifBlank { null },
                        estimatedLengthInches = state.lengthInches.toFloatOrNull(),
                        estimatedWidthInches = state.widthInches.toFloatOrNull(),
                        estimatedHeightInches = state.heightInches.toFloatOrNull(),
                        estimatedWeightOz = state.weightOz.toFloatOrNull(),
                        askingPrice = state.askingPrice.toDoubleOrNull(),
                        updatedAt = System.currentTimeMillis()
                    )
                )

                _uiState.update { it.copy(isSaving = false) }
                onSaved()
            } catch (e: Exception) {
                _uiState.update { it.copy(isSaving = false, error = e.message) }
            }
        }
    }

    fun savePhotosToGallery() {
        viewModelScope.launch(Dispatchers.IO) {
            val success = _uiState.value.photos.all { photo ->
                PhotoUtils.saveToGallery(appContext, photo.filePath)
            }
            _snackbarMessage.value = if (success) "saved" else "error"
        }
    }

    fun clearSnackbar() {
        _snackbarMessage.value = null
    }

    fun deleteItem(onDeleted: () -> Unit) {
        viewModelScope.launch {
            itemDao.deleteById(itemId)
            onDeleted()
        }
    }
}
