package com.vendedor.app.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vendedor.app.core.data.local.dao.ItemDao
import com.vendedor.app.core.data.local.dao.ItemPhotoDao
import com.vendedor.app.core.data.local.dao.ListingDao
import com.vendedor.app.core.data.local.dao.PriceResearchDao
import com.vendedor.app.core.data.local.dao.ShippingInfoDao
import com.vendedor.app.core.data.local.entity.ItemEntity
import com.vendedor.app.core.data.local.entity.ItemPhotoEntity
import com.vendedor.app.core.data.local.entity.ListingEntity
import com.vendedor.app.core.data.local.entity.PriceResearchEntity
import com.vendedor.app.core.data.local.entity.ShippingInfoEntity

@Database(
    entities = [
        ItemEntity::class,
        ItemPhotoEntity::class,
        ListingEntity::class,
        PriceResearchEntity::class,
        ShippingInfoEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class VendedorDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun itemPhotoDao(): ItemPhotoDao
    abstract fun listingDao(): ListingDao
    abstract fun priceResearchDao(): PriceResearchDao
    abstract fun shippingInfoDao(): ShippingInfoDao
}
