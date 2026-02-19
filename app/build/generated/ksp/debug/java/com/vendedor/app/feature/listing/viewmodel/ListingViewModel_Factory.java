package com.vendedor.app.feature.listing.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import com.vendedor.app.core.data.local.dao.ItemDao;
import com.vendedor.app.core.data.local.dao.ItemPhotoDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class ListingViewModel_Factory implements Factory<ListingViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<ItemDao> itemDaoProvider;

  private final Provider<ItemPhotoDao> itemPhotoDaoProvider;

  public ListingViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ItemDao> itemDaoProvider, Provider<ItemPhotoDao> itemPhotoDaoProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.itemDaoProvider = itemDaoProvider;
    this.itemPhotoDaoProvider = itemPhotoDaoProvider;
  }

  @Override
  public ListingViewModel get() {
    return newInstance(savedStateHandleProvider.get(), itemDaoProvider.get(), itemPhotoDaoProvider.get());
  }

  public static ListingViewModel_Factory create(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ItemDao> itemDaoProvider, Provider<ItemPhotoDao> itemPhotoDaoProvider) {
    return new ListingViewModel_Factory(savedStateHandleProvider, itemDaoProvider, itemPhotoDaoProvider);
  }

  public static ListingViewModel newInstance(SavedStateHandle savedStateHandle, ItemDao itemDao,
      ItemPhotoDao itemPhotoDao) {
    return new ListingViewModel(savedStateHandle, itemDao, itemPhotoDao);
  }
}
