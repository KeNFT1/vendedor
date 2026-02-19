package com.vendedor.app.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Orange40,
    onPrimary = Gray99,
    primaryContainer = Orange90,
    onPrimaryContainer = Gray10,
    secondary = Teal40,
    onSecondary = Gray99,
    secondaryContainer = Teal90,
    onSecondaryContainer = Gray10,
    tertiary = Purple40,
    background = Gray99,
    onBackground = Gray10,
    surface = Gray99,
    onSurface = Gray10,
    surfaceVariant = Gray95,
    error = ErrorRed
)

private val DarkColorScheme = darkColorScheme(
    primary = Orange80,
    onPrimary = Gray10,
    primaryContainer = Orange40,
    onPrimaryContainer = Gray99,
    secondary = Teal80,
    onSecondary = Gray10,
    secondaryContainer = Teal40,
    onSecondaryContainer = Gray99,
    tertiary = Purple80,
    background = Gray10,
    onBackground = Gray90,
    surface = Gray20,
    onSurface = Gray90,
    error = ErrorRed
)

@Composable
fun VendedorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = VendedorTypography,
        content = content
    )
}
