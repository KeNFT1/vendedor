package com.vendedor.app.di;

import com.vendedor.app.core.data.local.VendedorDatabase;
import com.vendedor.app.core.data.local.dao.PriceResearchDao;
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
public final class DatabaseModule_ProvidePriceResearchDaoFactory implements Factory<PriceResearchDao> {
  private final Provider<VendedorDatabase> databaseProvider;

  public DatabaseModule_ProvidePriceResearchDaoFactory(
      Provider<VendedorDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PriceResearchDao get() {
    return providePriceResearchDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvidePriceResearchDaoFactory create(
      Provider<VendedorDatabase> databaseProvider) {
    return new DatabaseModule_ProvidePriceResearchDaoFactory(databaseProvider);
  }

  public static PriceResearchDao providePriceResearchDao(VendedorDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePriceResearchDao(database));
  }
}
