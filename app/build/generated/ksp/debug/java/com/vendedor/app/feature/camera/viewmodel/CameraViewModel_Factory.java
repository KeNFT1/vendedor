package com.vendedor.app.feature.camera.viewmodel;

import com.vendedor.app.core.data.local.dao.ItemDao;
import com.vendedor.app.core.data.local.dao.ItemPhotoDao;
import com.vendedor.app.feature.camera.data.PhotoStorageManager;
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
public final class CameraViewModel_Factory implements Factory<CameraViewModel> {
  private final Provider<PhotoStorageManager> photoStorageManagerProvider;

  private final Provider<ItemDao> itemDaoProvider;

  private final Provider<ItemPhotoDao> itemPhotoDaoProvider;

  public CameraViewModel_Factory(Provider<PhotoStorageManager> photoStorageManagerProvider,
      Provider<ItemDao> itemDaoProvider, Provider<ItemPhotoDao> itemPhotoDaoProvider) {
    this.photoStorageManagerProvider = photoStorageManagerProvider;
    this.itemDaoProvider = itemDaoProvider;
    this.itemPhotoDaoProvider = itemPhotoDaoProvider;
  }

  @Override
  public CameraViewModel get() {
    return newInstance(photoStorageManagerProvider.get(), itemDaoProvider.get(), itemPhotoDaoProvider.get());
  }

  public static CameraViewModel_Factory create(
      Provider<PhotoStorageManager> photoStorageManagerProvider, Provider<ItemDao> itemDaoProvider,
      Provider<ItemPhotoDao> itemPhotoDaoProvider) {
    return new CameraViewModel_Factory(photoStorageManagerProvider, itemDaoProvider, itemPhotoDaoProvider);
  }

  public static CameraViewModel newInstance(PhotoStorageManager photoStorageManager,
      ItemDao itemDao, ItemPhotoDao itemPhotoDao) {
    return new CameraViewModel(photoStorageManager, itemDao, itemPhotoDao);
  }
}
