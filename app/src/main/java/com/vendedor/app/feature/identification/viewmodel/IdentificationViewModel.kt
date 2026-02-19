package com.vendedor.app.feature.identification.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vendedor.app.core.data.local.dao.ItemDao
import com.vendedor.app.core.data.local.dao.ItemPhotoDao
import com.vendedor.app.core.data.local.entity.ItemPhotoEntity
import com.vendedor.app.feature.identification.data.GeminiVisionService
import com.vendedor.app.feature.identification.data.model.AiIdentificationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class IdentificationUiState(
    val itemId: Long = 0,
    val photos: List<ItemPhotoEntity> = emptyList(),
    val primaryPhotoPath: String? = null,
    val isIdentifying: Boolean = false,
    val identification: AiIdentificationResponse? = null,
    // Editable fields (initialized from AI response)
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val brand: String = "",
    val condition: String = "",
    val lengthInches: String = "",
    val widthInches: String = "",
    val heightInches: String = "",
    val weightOz: String = "",
    val error: String? = null
)

@HiltViewModel
class IdentificationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val itemDao: ItemDao,
    private val itemPhotoDao: ItemPhotoDao,
    private val geminiVisionService: GeminiVisionService
) : ViewModel() {

    private val itemId: Long = savedStateHandle["itemId"] ?: 0L

    private val _uiState = MutableStateFlow(IdentificationUiState(itemId = itemId))
    val uiState: StateFlow<IdentificationUiState> = _uiState.asStateFlow()

    init {
        loadPhotos()
    }

    private fun loadPhotos() {
        viewModelScope.launch {
            val photos = itemPhotoDao.getPhotosForItem(itemId).first()
            val primary = photos.firstOrNull { it.isPrimary } ?: photos.firstOrNull()
            _uiState.update {
                it.copy(
                    photos = photos,
                    primaryPhotoPath = primary?.filePath
                )
            }
        }
    }

    fun identifyItem() {
        val photoPath = _uiState.value.primaryPhotoPath ?: return

        viewModelScope.launch {
            _uiState.update { it.copy(isIdentifying = true, error = null) }
            try {
                val result = geminiVisionService.identifyItem(photoPath)
                _uiState.update {
                    it.copy(
                        isIdentifying = false,
                        identification = result,
                        title = result.title,
                        description = result.description,
                        category = result.category ?: "",
                        brand = result.brand ?: "",
                        condition = result.condition ?: "",
                        lengthInches = result.estimatedLengthInches?.toString() ?: "",
                        widthInches = result.estimatedWidthInches?.toString() ?: "",
                        heightInches = result.estimatedHeightInches?.toString() ?: "",
                        weightOz = result.estimatedWeightOz?.toString() ?: ""
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isIdentifying = false, error = e.message)
                }
            }
        }
    }

    fun updateTitle(value: String) { _uiState.update { it.copy(title = value) } }
    fun updateDescription(value: String) { _uiState.update { it.copy(description = value) } }
    fun updateCategory(value: String) { _uiState.update { it.copy(category = value) } }
    fun updateBrand(value: String) { _uiState.update { it.copy(brand = value) } }
    fun updateCondition(value: String) { _uiState.update { it.copy(condition = value) } }
    fun updateLength(value: String) { _uiState.update { it.copy(lengthInches = value) } }
    fun updateWidth(value: String) { _uiState.update { it.copy(widthInches = value) } }
    fun updateHeight(value: String) { _uiState.update { it.copy(heightInches = value) } }
    fun updateWeight(value: String) { _uiState.update { it.copy(weightOz = value) } }

    fun saveAndContinue(onSaved: () -> Unit) {
        viewModelScope.launch {
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
                    aiIdentificationJson = state.identification?.toString(),
                    updatedAt = System.currentTimeMillis()
                )
            )
            onSaved()
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
