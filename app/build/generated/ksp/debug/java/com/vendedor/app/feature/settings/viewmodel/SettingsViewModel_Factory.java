package com.vendedor.app.feature.settings.viewmodel;

import com.vendedor.app.core.data.preferences.ApiKeyManager;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<ApiKeyManager> apiKeyManagerProvider;

  public SettingsViewModel_Factory(Provider<ApiKeyManager> apiKeyManagerProvider) {
    this.apiKeyManagerProvider = apiKeyManagerProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(apiKeyManagerProvider.get());
  }

  public static SettingsViewModel_Factory create(Provider<ApiKeyManager> apiKeyManagerProvider) {
    return new SettingsViewModel_Factory(apiKeyManagerProvider);
  }

  public static SettingsViewModel newInstance(ApiKeyManager apiKeyManager) {
    return new SettingsViewModel(apiKeyManager);
  }
}
