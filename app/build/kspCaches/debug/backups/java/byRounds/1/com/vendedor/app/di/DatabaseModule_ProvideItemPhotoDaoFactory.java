package com.vendedor.app.di;

import com.vendedor.app.core.data.local.VendedorDatabase;
import com.vendedor.app.core.data.local.dao.ItemPhotoDao;
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
public final class DatabaseModule_ProvideItemPhotoDaoFactory implements Factory<ItemPhotoDao> {
  private final Provider<VendedorDatabase> databaseProvider;

  public DatabaseModule_ProvideItemPhotoDaoFactory(Provider<VendedorDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ItemPhotoDao get() {
    return provideItemPhotoDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideItemPhotoDaoFactory create(
      Provider<VendedorDatabase> databaseProvider) {
    return new DatabaseModule_ProvideItemPhotoDaoFactory(databaseProvider);
  }

  public static ItemPhotoDao provideItemPhotoDao(VendedorDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideItemPhotoDao(database));
  }
}
