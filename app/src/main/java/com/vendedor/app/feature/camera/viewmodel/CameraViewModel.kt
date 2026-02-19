package com.vendedor.app.feature.camera.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vendedor.app.core.data.local.dao.ItemDao
import com.vendedor.app.core.data.local.dao.ItemPhotoDao
import com.vendedor.app.core.data.local.entity.ItemEntity
import com.vendedor.app.core.data.local.entity.ItemPhotoEntity
import com.vendedor.app.feature.camera.data.PhotoStorageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

data class CapturedPhoto(
    val filePath: String,
    val isPrimary: Boolean = false
)

data class CameraUiState(
    val capturedPhotos: List<CapturedPhoto> = emptyList(),
    val isSaving: Boolean = false,
    val savedItemId: Long? = null,
    val error: String? = null
)

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val photoStorageManager: PhotoStorageManager,
    private val itemDao: ItemDao,
    private val itemPhotoDao: ItemPhotoDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(CameraUiState())
    val uiState: StateFlow<CameraUiState> = _uiState.asStateFlow()

    fun onPhotoCaptured(file: File) {
        viewModelScope.launch {
            try {
                val savedPath = photoStorageManager.savePhotoFromFile(file)
                val isFirst = _uiState.value.capturedPhotos.isEmpty()
                _uiState.update { state ->
                    state.copy(
                        capturedPhotos = state.capturedPhotos + CapturedPhoto(
                            filePath = savedPath,
                            isPrimary = isFirst
                        )
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun onPhotoPickedFromGallery(uri: Uri) {
        viewModelScope.launch {
            try {
                val savedPath = photoStorageManager.savePhoto(uri)
                val isFirst = _uiState.value.capturedPhotos.isEmpty()
                _uiState.update { state ->
                    state.copy(
                        capturedPhotos = state.capturedPhotos + CapturedPhoto(
                            filePath = savedPath,
                            isPrimary = isFirst
                        )
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun setPrimaryPhoto(index: Int) {
        _uiState.update { state ->
            state.copy(
                capturedPhotos = state.capturedPhotos.mapIndexed { i, photo ->
                    photo.copy(isPrimary = i == index)
                }
            )
        }
    }

    fun removePhoto(index: Int) {
        viewModelScope.launch {
            val photo = _uiState.value.capturedPhotos.getOrNull(index) ?: return@launch
            photoStorageManager.deletePhoto(photo.filePath)

            _uiState.update { state ->
                val updated = state.capturedPhotos.toMutableList().apply { removeAt(index) }
                // If we removed the primary, make the first one primary
                val withPrimary = if (updated.none { it.isPrimary } && updated.isNotEmpty()) {
                    updated.mapIndexed { i, p -> p.copy(isPrimary = i == 0) }
                } else {
                    updated
                }
                state.copy(capturedPhotos = withPrimary)
            }
        }
    }

    fun saveItemWithPhotos() {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            try {
                // Create a draft item
                val itemId = itemDao.insert(
                    ItemEntity(
                        title = "",
                        description = "",
                        status = "draft"
                    )
                )

                // Save photos linked to the item
                _uiState.value.capturedPhotos.forEachIndexed { index, photo ->
                    itemPhotoDao.insert(
                        ItemPhotoEntity(
                            itemId = itemId,
                            filePath = photo.filePath,
                            isPrimary = photo.isPrimary,
                            sortOrder = index
                        )
                    )
                }

                _uiState.update { it.copy(isSaving = false, savedItemId = itemId) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isSaving = false, error = e.message) }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun createTempFile(): File = photoStorageManager.createTempPhotoFile()
}
