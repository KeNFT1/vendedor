package com.vendedor.app.feature.publishing.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vendedor.app.core.data.local.dao.ItemDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PublishUiState(
    val title: String = "",
    val exportText: String = ""
)

@HiltViewModel
class PublishViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val itemDao: ItemDao
) : ViewModel() {

    private val itemId: Long = savedStateHandle["itemId"] ?: 0L

    private val _uiState = MutableStateFlow(PublishUiState())
    val uiState: StateFlow<PublishUiState> = _uiState.asStateFlow()

    init {
        loadItem()
    }

    private fun loadItem() {
        viewModelScope.launch {
            val item = itemDao.getItemById(itemId) ?: return@launch

            val exportText = buildString {
                appendLine(item.title)
                appendLine()
                if (item.askingPrice != null) {
                    appendLine("💰 \$${item.askingPrice}")
                    appendLine()
                }
                if (item.condition != null) {
                    appendLine("📦 ${item.condition}")
                }
                appendLine()
                appendLine(item.description)
                appendLine()
                if (item.estimatedLengthInches != null) {
                    appendLine("📏 ${item.estimatedLengthInches} x ${item.estimatedWidthInches} x ${item.estimatedHeightInches} in")
                }
                if (item.estimatedWeightOz != null) {
                    appendLine("⚖️ ${item.estimatedWeightOz} oz")
                }
                if (item.brand != null) {
                    appendLine("🏷️ ${item.brand}")
                }
            }

            _uiState.update {
                it.copy(
                    title = item.title,
                    exportText = exportText.trim()
                )
            }
        }
    }
}
