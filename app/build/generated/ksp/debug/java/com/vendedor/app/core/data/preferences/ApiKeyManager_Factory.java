package com.vendedor.app.core.data.preferences;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ApiKeyManager_Factory implements Factory<ApiKeyManager> {
  private final Provider<Context> contextProvider;

  public ApiKeyManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ApiKeyManager get() {
    return newInstance(contextProvider.get());
  }

  public static ApiKeyManager_Factory create(Provider<Context> contextProvider) {
    return new ApiKeyManager_Factory(contextProvider);
  }

  public static ApiKeyManager newInstance(Context context) {
    return new ApiKeyManager(context);
  }
}
