package com.vendedor.app.di;

import com.vendedor.app.core.data.local.VendedorDatabase;
import com.vendedor.app.core.data.local.dao.ListingDao;
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
public final class DatabaseModule_ProvideListingDaoFactory implements Factory<ListingDao> {
  private final Provider<VendedorDatabase> databaseProvider;

  public DatabaseModule_ProvideListingDaoFactory(Provider<VendedorDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ListingDao get() {
    return provideListingDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideListingDaoFactory create(
      Provider<VendedorDatabase> databaseProvider) {
    return new DatabaseModule_ProvideListingDaoFactory(databaseProvider);
  }

  public static ListingDao provideListingDao(VendedorDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideListingDao(database));
  }
}
