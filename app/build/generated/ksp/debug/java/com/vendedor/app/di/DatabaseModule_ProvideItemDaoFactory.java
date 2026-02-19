package com.vendedor.app.di;

import com.vendedor.app.core.data.local.VendedorDatabase;
import com.vendedor.app.core.data.local.dao.ItemDao;
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
public final class DatabaseModule_ProvideItemDaoFactory implements Factory<ItemDao> {
  private final Provider<VendedorDatabase> databaseProvider;

  public DatabaseModule_ProvideItemDaoFactory(Provider<VendedorDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ItemDao get() {
    return provideItemDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideItemDaoFactory create(
      Provider<VendedorDatabase> databaseProvider) {
    return new DatabaseModule_ProvideItemDaoFactory(databaseProvider);
  }

  public static ItemDao provideItemDao(VendedorDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideItemDao(database));
  }
}
