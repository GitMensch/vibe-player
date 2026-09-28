package dev.fiedri.vibe.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalVibeColors = staticCompositionLocalOf { VibeDarkColors }
val LocalVibeTypography = staticCompositionLocalOf { VibeTypography }

// Mapeo a Material 3 para componentes estándar de Material
val MaterialTypography = Typography(
    displayLarge = VibeTypography.display,
    titleLarge = VibeTypography.titleLarge,
    titleMedium = VibeTypography.titleMedium,
    bodyLarge = VibeTypography.bodyLarge,
    bodyMedium = VibeTypography.bodyMedium,
    labelSmall = VibeTypography.caption
)

private val VibeDarkColorScheme = darkColorScheme(
    primary = VibeDarkColors.primary,
    onPrimary = VibeDarkColors.primaryForeground,
    primaryContainer = VibeDarkColors.muted,
    onPrimaryContainer = VibeDarkColors.foreground,
    inversePrimary = VibeDarkColors.foreground,
    secondary = VibeDarkColors.secondary,
    onSecondary = VibeDarkColors.secondaryForeground,
    secondaryContainer = VibeDarkColors.muted,
    onSecondaryContainer = VibeDarkColors.foreground,
    tertiary = VibeDarkColors.secondary,
    onTertiary = VibeDarkColors.secondaryForeground,
    tertiaryContainer = VibeDarkColors.muted,
    onTertiaryContainer = VibeDarkColors.foreground,
    background = VibeDarkColors.background,
    onBackground = VibeDarkColors.foreground,
    surface = VibeDarkColors.cards,
    onSurface = VibeDarkColors.foreground,
    surfaceVariant = VibeDarkColors.muted,
    onSurfaceVariant = VibeDarkColors.mutedForeground,
    surfaceTint = VibeDarkColors.primary,
    inverseSurface = VibeDarkColors.foreground,
    inverseOnSurface = VibeDarkColors.background,
    error = VibeDarkColors.destructive,
    onError = VibeDarkColors.destructiveForeground,
    errorContainer = VibeDarkColors.muted,
    onErrorContainer = VibeDarkColors.foreground,
    outline = VibeDarkColors.border,
    outlineVariant = VibeDarkColors.border,
    surfaceBright = VibeDarkColors.popover,
    surfaceContainer = VibeDarkColors.cards,
    surfaceContainerHigh = VibeDarkColors.popover,
    surfaceContainerHighest = VibeDarkColors.muted,
    surfaceContainerLow = VibeDarkColors.cards,
    surfaceContainerLowest = VibeDarkColors.background,
    surfaceDim = VibeDarkColors.background,
    primaryFixed = VibeDarkColors.primary,
    primaryFixedDim = VibeDarkColors.muted,
    onPrimaryFixed = VibeDarkColors.primaryForeground,
    onPrimaryFixedVariant = VibeDarkColors.foreground,
    secondaryFixed = VibeDarkColors.secondary,
    secondaryFixedDim = VibeDarkColors.muted,
    onSecondaryFixed = VibeDarkColors.secondaryForeground,
    onSecondaryFixedVariant = VibeDarkColors.foreground,
    tertiaryFixed = VibeDarkColors.secondary,
    tertiaryFixedDim = VibeDarkColors.muted,
    onTertiaryFixed = VibeDarkColors.secondaryForeground,
    onTertiaryFixedVariant = VibeDarkColors.foreground
)

private val VibeLightColorScheme = lightColorScheme(
    primary = VibeLightColors.primary,
    onPrimary = VibeLightColors.primaryForeground,
    primaryContainer = VibeLightColors.muted,
    onPrimaryContainer = VibeLightColors.foreground,
    inversePrimary = VibeLightColors.foreground,
    secondary = VibeLightColors.secondary,
    onSecondary = VibeLightColors.secondaryForeground,
    secondaryContainer = VibeLightColors.muted,
    onSecondaryContainer = VibeLightColors.foreground,
    tertiary = VibeLightColors.secondary,
    onTertiary = VibeLightColors.secondaryForeground,
    tertiaryContainer = VibeLightColors.muted,
    onTertiaryContainer = VibeLightColors.foreground,
    background = VibeLightColors.background,
    onBackground = VibeLightColors.foreground,
    surface = VibeLightColors.cards,
    onSurface = VibeLightColors.foreground,
    surfaceVariant = VibeLightColors.muted,
    onSurfaceVariant = VibeLightColors.mutedForeground,
    surfaceTint = VibeLightColors.primary,
    inverseSurface = VibeLightColors.foreground,
    inverseOnSurface = VibeLightColors.background,
    error = VibeLightColors.destructive,
    onError = VibeLightColors.destructiveForeground,
    errorContainer = VibeLightColors.muted,
    onErrorContainer = VibeLightColors.foreground,
    outline = VibeLightColors.border,
    outlineVariant = VibeLightColors.border,
    surfaceBright = VibeLightColors.popover,
    surfaceContainer = VibeLightColors.cards,
    surfaceContainerHigh = VibeLightColors.popover,
    surfaceContainerHighest = VibeLightColors.muted,
    surfaceContainerLow = VibeLightColors.cards,
    surfaceContainerLowest = VibeLightColors.background,
    surfaceDim = VibeLightColors.background,
    primaryFixed = VibeLightColors.primary,
    primaryFixedDim = VibeLightColors.muted,
    onPrimaryFixed = VibeLightColors.primaryForeground,
    onPrimaryFixedVariant = VibeLightColors.foreground,
    secondaryFixed = VibeLightColors.secondary,
    secondaryFixedDim = VibeLightColors.muted,
    onSecondaryFixed = VibeLightColors.secondaryForeground,
    onSecondaryFixedVariant = VibeLightColors.foreground,
    tertiaryFixed = VibeLightColors.secondary,
    tertiaryFixedDim = VibeLightColors.muted,
    onTertiaryFixed = VibeLightColors.secondaryForeground,
    onTertiaryFixedVariant = VibeLightColors.foreground
)

@Composable
fun VibeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    //val colors = if (darkTheme) VibeDarkColors else VibeLightColors
val colors = VibeDarkColors
    CompositionLocalProvider(
        LocalVibeColors provides colors,
        LocalVibeTypography provides VibeTypography,
        LocalContentColor provides colors.foreground

    ) {
        MaterialTheme(
            colorScheme = if (darkTheme) VibeDarkColorScheme else VibeLightColorScheme,
            typography = MaterialTypography,
            content = content
        )
    }
}

object VibeTheme {
    val colors: VibeColors
        @Composable
        @ReadOnlyComposable
        get() = LocalVibeColors.current

    val typography: VibeTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalVibeTypography.current
}
