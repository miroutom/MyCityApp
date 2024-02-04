package com.example.mycity.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = dark_primary,
    primaryContainer = dark_primary_container,
    onPrimary = dark_on_primary,
    onPrimaryContainer = dark_on_primary_container,
    secondary = dark_secondary,
    onSecondary = dark_on_secondary,
    secondaryContainer = dark_secondary_container,
    onSecondaryContainer = dark_on_secondary_container,
    tertiary = dark_tertiary,
    onTertiary = dark_on_tertiary,
    tertiaryContainer = dark_tertiary_container,
    onTertiaryContainer = dark_on_tertiary_container,
    error = dark_error,
    onError = dark_on_error,
    errorContainer = dark_error_container,
    onErrorContainer = dark_on_error_container,
    background = dark_background,
    onBackground = dark_on_background,
    surface = dark_surface,
    onSurface = dark_on_surface,
    outline = dark_outline,
    surfaceVariant = dark_surface_variant,
    onSurfaceVariant = dark_on_surface_variant
)

private val LightColorScheme = lightColorScheme(
    primary = light_primary,
    primaryContainer = light_primary_container,
    onPrimary = light_on_primary,
    onPrimaryContainer = light_on_primary_container,
    secondary = light_secondary,
    onSecondary = light_on_secondary,
    secondaryContainer = light_secondary_container,
    onSecondaryContainer = light_on_secondary_container,
    tertiary = light_tertiary,
    onTertiary = light_on_tertiary,
    tertiaryContainer = light_tertiary_container,
    onTertiaryContainer = light_on_tertiary_container,
    error = light_error,
    onError = light_on_error,
    errorContainer = light_error_container,
    onErrorContainer = light_on_error_container,
    background = light_background,
    onBackground = light_on_background,
    surface = light_surface,
    onSurface = light_on_surface,
    outline = light_outline,
    surfaceVariant = light_surface_variant,
    onSurfaceVariant = light_on_surface_variant
)

@Composable
fun MyCityTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}