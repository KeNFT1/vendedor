package com.vendedor.app.feature.listing.viewmodel;

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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<ItemDao> itemDaoProvider;

  public HomeViewModel_Factory(Provider<ItemDao> itemDaoProvider) {
    this.itemDaoProvider = itemDaoProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(itemDaoProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<ItemDao> itemDaoProvider) {
    return new HomeViewModel_Factory(itemDaoProvider);
  }

  public static HomeViewModel newInstance(ItemDao itemDao) {
    return new HomeViewModel(itemDao);
  }
}
