package com.vendedor.app.di

import android.content.Context
import androidx.room.Room
import com.vendedor.app.core.data.local.VendedorDatabase
import com.vendedor.app.core.data.local.dao.ItemDao
import com.vendedor.app.core.data.local.dao.ItemPhotoDao
import com.vendedor.app.core.data.local.dao.ListingDao
import com.vendedor.app.core.data.local.dao.PriceResearchDao
import com.vendedor.app.core.data.local.dao.ShippingInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): VendedorDatabase {
        return Room.databaseBuilder(
            context,
            VendedorDatabase::class.java,
            "vendedor_database"
        ).build()
    }

    @Provides
    fun provideItemDao(database: VendedorDatabase): ItemDao = database.itemDao()

    @Provides
    fun provideItemPhotoDao(database: VendedorDatabase): ItemPhotoDao = database.itemPhotoDao()

    @Provides
    fun provideListingDao(database: VendedorDatabase): ListingDao = database.listingDao()

    @Provides
    fun providePriceResearchDao(database: VendedorDatabase): PriceResearchDao = database.priceResearchDao()

    @Provides
    fun provideShippingInfoDao(database: VendedorDatabase): ShippingInfoDao = database.shippingInfoDao()
}
