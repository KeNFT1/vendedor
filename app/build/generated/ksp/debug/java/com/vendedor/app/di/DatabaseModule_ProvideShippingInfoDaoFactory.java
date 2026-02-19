package com.vendedor.app.di;

import com.vendedor.app.core.data.local.VendedorDatabase;
import com.vendedor.app.core.data.local.dao.ShippingInfoDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideShippingInfoDaoFactory implements Factory<ShippingInfoDao> {
  private final Provider<VendedorDatabase> databaseProvider;

  public DatabaseModule_ProvideShippingInfoDaoFactory(Provider<VendedorDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ShippingInfoDao get() {
    return provideShippingInfoDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideShippingInfoDaoFactory create(
      Provider<VendedorDatabase> databaseProvider) {
    return new DatabaseModule_ProvideShippingInfoDaoFactory(databaseProvider);
  }

  public static ShippingInfoDao provideShippingInfoDao(VendedorDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideShippingInfoDao(database));
  }
}
