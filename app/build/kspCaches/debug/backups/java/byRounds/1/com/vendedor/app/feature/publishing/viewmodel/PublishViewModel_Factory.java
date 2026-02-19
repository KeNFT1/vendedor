package com.vendedor.app.feature.publishing.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import com.vendedor.app.core.data.local.dao.ItemDao;
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
public final class PublishViewModel_Factory implements Factory<PublishViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<ItemDao> itemDaoProvider;

  public PublishViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ItemDao> itemDaoProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.itemDaoProvider = itemDaoProvider;
  }

  @Override
  public PublishViewModel get() {
    return newInstance(savedStateHandleProvider.get(), itemDaoProvider.get());
  }

  public static PublishViewModel_Factory create(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ItemDao> itemDaoProvider) {
    return new PublishViewModel_Factory(savedStateHandleProvider, itemDaoProvider);
  }

  public static PublishViewModel newInstance(SavedStateHandle savedStateHandle, ItemDao itemDao) {
    return new PublishViewModel(savedStateHandle, itemDao);
  }
}
