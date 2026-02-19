package com.vendedor.app.feature.shipping.viewmodel;

import android.content.Context;
import androidx.lifecycle.SavedStateHandle;
import com.vendedor.app.core.data.local.dao.ItemDao;
import com.vendedor.app.core.data.local.dao.ShippingInfoDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ShippingViewModel_Factory implements Factory<ShippingViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<ItemDao> itemDaoProvider;

  private final Provider<ShippingInfoDao> shippingInfoDaoProvider;

  private final Provider<Context> contextProvider;

  public ShippingViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<ItemDao> itemDaoProvider, Provider<ShippingInfoDao> shippingInfoDaoProvider,
      Provider<Context> contextProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.itemDaoProvider = itemDaoProvider;
    this.shippingInfoDaoProvider = shippingInfoDaoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public ShippingViewModel get() {
    return newInstance(savedStateHandleProvider.get(), itemDaoProvider.get(), shippingInfoDaoProvider.get(), contextProvider.get());
  }

  public static ShippingViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider, Provider<ItemDao> itemDaoProvider,
      Provider<ShippingInfoDao> shippingInfoDaoProvider, Provider<Context> contextProvider) {
    return new ShippingViewModel_Factory(savedStateHandleProvider, itemDaoProvider, shippingInfoDaoProvider, contextProvider);
  }

  public static ShippingViewModel newInstance(SavedStateHandle savedStateHandle, ItemDao itemDao,
      ShippingInfoDao shippingInfoDao, Context context) {
    return new ShippingViewModel(savedStateHandle, itemDao, shippingInfoDao, context);
  }
}
