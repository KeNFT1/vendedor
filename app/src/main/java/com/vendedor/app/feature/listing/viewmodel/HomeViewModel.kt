package com.vendedor.app.feature.listing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vendedor.app.core.data.local.dao.ItemDao
import com.vendedor.app.core.data.local.dao.ItemPhotoDao
import com.vendedor.app.feature.listing.model.ItemWithThumbnail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    itemDao: ItemDao,
    itemPhotoDao: ItemPhotoDao
) : ViewModel() {

    val items: StateFlow<List<ItemWithThumbnail>> = combine(
        itemDao.getAllItems(),
        itemPhotoDao.getAllPrimaryPhotos()
    ) { items, primaryPhotos ->
        val photoMap = primaryPhotos.associateBy { it.itemId }
        items.map { item ->
            ItemWithThumbnail(
                id = item.id,
                title = item.title,
                suggestedPriceLow = item.suggestedPriceLow,
                suggestedPriceHigh = item.suggestedPriceHigh,
                askingPrice = item.askingPrice,
                status = item.status,
                createdAt = item.createdAt,
                primaryPhotoPath = photoMap[item.id]?.filePath
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}
