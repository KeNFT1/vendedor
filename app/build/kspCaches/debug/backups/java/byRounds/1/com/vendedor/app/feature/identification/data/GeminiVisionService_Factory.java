package com.vendedor.app.feature.identification.data;

import com.squareup.moshi.Moshi;
import com.vendedor.app.core.data.preferences.ApiKeyManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class GeminiVisionService_Factory implements Factory<GeminiVisionService> {
  private final Provider<ApiKeyManager> apiKeyManagerProvider;

  private final Provider<Moshi> moshiProvider;

  public GeminiVisionService_Factory(Provider<ApiKeyManager> apiKeyManagerProvider,
      Provider<Moshi> moshiProvider) {
    this.apiKeyManagerProvider = apiKeyManagerProvider;
    this.moshiProvider = moshiProvider;
  }

  @Override
  public GeminiVisionService get() {
    return newInstance(apiKeyManagerProvider.get(), moshiProvider.get());
  }

  public static GeminiVisionService_Factory create(Provider<ApiKeyManager> apiKeyManagerProvider,
      Provider<Moshi> moshiProvider) {
    return new GeminiVisionService_Factory(apiKeyManagerProvider, moshiProvider);
  }

  public static GeminiVisionService newInstance(ApiKeyManager apiKeyManager, Moshi moshi) {
    return new GeminiVisionService(apiKeyManager, moshi);
  }
}
