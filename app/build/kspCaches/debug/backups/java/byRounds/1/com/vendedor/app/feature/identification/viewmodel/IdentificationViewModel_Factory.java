package com.vendedor.app.feature.identification.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import com.vendedor.app.core.data.local.dao.ItemDao;
import com.vendedor.app.core.data.local.dao.ItemPhotoDao;
import com.vendedor.app.feature.identification.data.GeminiVisionService;
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
public final class IdentificationViewModel_Factory implements Factory<IdentificationViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<ItemDao> itemDaoProvider;

  private final Provider<ItemPhotoDao> itemPhotoDaoProvider;

  private final Provider<GeminiVisionService> geminiVisionServiceProvider;

  public IdentificationViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ItemDao> itemDaoProvider, Provider<ItemPhotoDao> itemPhotoDaoProvider,
      Provider<GeminiVisionService> geminiVisionServiceProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.itemDaoProvider = itemDaoProvider;
    this.itemPhotoDaoProvider = itemPhotoDaoProvider;
    this.geminiVisionServiceProvider = geminiVisionServiceProvider;
  }

  @Override
  public IdentificationViewModel get() {
    return newInstance(savedStateHandleProvider.get(), itemDaoProvider.get(), itemPhotoDaoProvider.get(), geminiVisionServiceProvider.get());
  }

  public static IdentificationViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider, Provider<ItemDao> itemDaoProvider,
      Provider<ItemPhotoDao> itemPhotoDaoProvider,
      Provider<GeminiVisionService> geminiVisionServiceProvider) {
    return new IdentificationViewModel_Factory(savedStateHandleProvider, itemDaoProvider, itemPhotoDaoProvider, geminiVisionServiceProvider);
  }

  public static IdentificationViewModel newInstance(SavedStateHandle savedStateHandle,
      ItemDao itemDao, ItemPhotoDao itemPhotoDao, GeminiVisionService geminiVisionService) {
    return new IdentificationViewModel(savedStateHandle, itemDao, itemPhotoDao, geminiVisionService);
  }
}
