package com.example.spanienakanapie.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.spanienakanapie.backgroundDark
import com.example.spanienakanapie.backgroundDarkHighContrast
import com.example.spanienakanapie.backgroundDarkMediumContrast
import com.example.spanienakanapie.backgroundLight
import com.example.spanienakanapie.backgroundLightHighContrast
import com.example.spanienakanapie.backgroundLightMediumContrast
import com.example.spanienakanapie.errorContainerDark
import com.example.spanienakanapie.errorContainerDarkHighContrast
import com.example.spanienakanapie.errorContainerDarkMediumContrast
import com.example.spanienakanapie.errorContainerLight
import com.example.spanienakanapie.errorContainerLightHighContrast
import com.example.spanienakanapie.errorContainerLightMediumContrast
import com.example.spanienakanapie.errorDark
import com.example.spanienakanapie.errorDarkHighContrast
import com.example.spanienakanapie.errorDarkMediumContrast
import com.example.spanienakanapie.errorLight
import com.example.spanienakanapie.errorLightHighContrast
import com.example.spanienakanapie.errorLightMediumContrast
import com.example.spanienakanapie.inverseOnSurfaceDark
import com.example.spanienakanapie.inverseOnSurfaceDarkHighContrast
import com.example.spanienakanapie.inverseOnSurfaceDarkMediumContrast
import com.example.spanienakanapie.inverseOnSurfaceLight
import com.example.spanienakanapie.inverseOnSurfaceLightHighContrast
import com.example.spanienakanapie.inverseOnSurfaceLightMediumContrast
import com.example.spanienakanapie.inversePrimaryDark
import com.example.spanienakanapie.inversePrimaryDarkHighContrast
import com.example.spanienakanapie.inversePrimaryDarkMediumContrast
import com.example.spanienakanapie.inversePrimaryLight
import com.example.spanienakanapie.inversePrimaryLightHighContrast
import com.example.spanienakanapie.inversePrimaryLightMediumContrast
import com.example.spanienakanapie.inverseSurfaceDark
import com.example.spanienakanapie.inverseSurfaceDarkHighContrast
import com.example.spanienakanapie.inverseSurfaceDarkMediumContrast
import com.example.spanienakanapie.inverseSurfaceLight
import com.example.spanienakanapie.inverseSurfaceLightHighContrast
import com.example.spanienakanapie.inverseSurfaceLightMediumContrast
import com.example.spanienakanapie.onBackgroundDark
import com.example.spanienakanapie.onBackgroundDarkHighContrast
import com.example.spanienakanapie.onBackgroundDarkMediumContrast
import com.example.spanienakanapie.onBackgroundLight
import com.example.spanienakanapie.onBackgroundLightHighContrast
import com.example.spanienakanapie.onBackgroundLightMediumContrast
import com.example.spanienakanapie.onErrorContainerDark
import com.example.spanienakanapie.onErrorContainerDarkHighContrast
import com.example.spanienakanapie.onErrorContainerDarkMediumContrast
import com.example.spanienakanapie.onErrorContainerLight
import com.example.spanienakanapie.onErrorContainerLightHighContrast
import com.example.spanienakanapie.onErrorContainerLightMediumContrast
import com.example.spanienakanapie.onErrorDark
import com.example.spanienakanapie.onErrorDarkHighContrast
import com.example.spanienakanapie.onErrorDarkMediumContrast
import com.example.spanienakanapie.onErrorLight
import com.example.spanienakanapie.onErrorLightHighContrast
import com.example.spanienakanapie.onErrorLightMediumContrast
import com.example.spanienakanapie.onPrimaryContainerDark
import com.example.spanienakanapie.onPrimaryContainerDarkHighContrast
import com.example.spanienakanapie.onPrimaryContainerDarkMediumContrast
import com.example.spanienakanapie.onPrimaryContainerLight
import com.example.spanienakanapie.onPrimaryContainerLightHighContrast
import com.example.spanienakanapie.onPrimaryContainerLightMediumContrast
import com.example.spanienakanapie.onPrimaryDark
import com.example.spanienakanapie.onPrimaryDarkHighContrast
import com.example.spanienakanapie.onPrimaryDarkMediumContrast
import com.example.spanienakanapie.onPrimaryLight
import com.example.spanienakanapie.onPrimaryLightHighContrast
import com.example.spanienakanapie.onPrimaryLightMediumContrast
import com.example.spanienakanapie.onSecondaryContainerDark
import com.example.spanienakanapie.onSecondaryContainerDarkHighContrast
import com.example.spanienakanapie.onSecondaryContainerDarkMediumContrast
import com.example.spanienakanapie.onSecondaryContainerLight
import com.example.spanienakanapie.onSecondaryContainerLightHighContrast
import com.example.spanienakanapie.onSecondaryContainerLightMediumContrast
import com.example.spanienakanapie.onSecondaryDark
import com.example.spanienakanapie.onSecondaryDarkHighContrast
import com.example.spanienakanapie.onSecondaryDarkMediumContrast
import com.example.spanienakanapie.onSecondaryLight
import com.example.spanienakanapie.onSecondaryLightHighContrast
import com.example.spanienakanapie.onSecondaryLightMediumContrast
import com.example.spanienakanapie.onSurfaceDark
import com.example.spanienakanapie.onSurfaceDarkHighContrast
import com.example.spanienakanapie.onSurfaceDarkMediumContrast
import com.example.spanienakanapie.onSurfaceLight
import com.example.spanienakanapie.onSurfaceLightHighContrast
import com.example.spanienakanapie.onSurfaceLightMediumContrast
import com.example.spanienakanapie.onSurfaceVariantDark
import com.example.spanienakanapie.onSurfaceVariantDarkHighContrast
import com.example.spanienakanapie.onSurfaceVariantDarkMediumContrast
import com.example.spanienakanapie.onSurfaceVariantLight
import com.example.spanienakanapie.onSurfaceVariantLightHighContrast
import com.example.spanienakanapie.onSurfaceVariantLightMediumContrast
import com.example.spanienakanapie.onTertiaryContainerDark
import com.example.spanienakanapie.onTertiaryContainerDarkHighContrast
import com.example.spanienakanapie.onTertiaryContainerDarkMediumContrast
import com.example.spanienakanapie.onTertiaryContainerLight
import com.example.spanienakanapie.onTertiaryContainerLightHighContrast
import com.example.spanienakanapie.onTertiaryContainerLightMediumContrast
import com.example.spanienakanapie.onTertiaryDark
import com.example.spanienakanapie.onTertiaryDarkHighContrast
import com.example.spanienakanapie.onTertiaryDarkMediumContrast
import com.example.spanienakanapie.onTertiaryLight
import com.example.spanienakanapie.onTertiaryLightHighContrast
import com.example.spanienakanapie.onTertiaryLightMediumContrast
import com.example.spanienakanapie.outlineDark
import com.example.spanienakanapie.outlineDarkHighContrast
import com.example.spanienakanapie.outlineDarkMediumContrast
import com.example.spanienakanapie.outlineLight
import com.example.spanienakanapie.outlineLightHighContrast
import com.example.spanienakanapie.outlineLightMediumContrast
import com.example.spanienakanapie.outlineVariantDark
import com.example.spanienakanapie.outlineVariantDarkHighContrast
import com.example.spanienakanapie.outlineVariantDarkMediumContrast
import com.example.spanienakanapie.outlineVariantLight
import com.example.spanienakanapie.outlineVariantLightHighContrast
import com.example.spanienakanapie.outlineVariantLightMediumContrast
import com.example.spanienakanapie.primaryContainerDark
import com.example.spanienakanapie.primaryContainerDarkHighContrast
import com.example.spanienakanapie.primaryContainerDarkMediumContrast
import com.example.spanienakanapie.primaryContainerLight
import com.example.spanienakanapie.primaryContainerLightHighContrast
import com.example.spanienakanapie.primaryContainerLightMediumContrast
import com.example.spanienakanapie.primaryDark
import com.example.spanienakanapie.primaryDarkHighContrast
import com.example.spanienakanapie.primaryDarkMediumContrast
import com.example.spanienakanapie.primaryLight
import com.example.spanienakanapie.primaryLightHighContrast
import com.example.spanienakanapie.primaryLightMediumContrast
import com.example.spanienakanapie.scrimDark
import com.example.spanienakanapie.scrimDarkHighContrast
import com.example.spanienakanapie.scrimDarkMediumContrast
import com.example.spanienakanapie.scrimLight
import com.example.spanienakanapie.scrimLightHighContrast
import com.example.spanienakanapie.scrimLightMediumContrast
import com.example.spanienakanapie.secondaryContainerDark
import com.example.spanienakanapie.secondaryContainerDarkHighContrast
import com.example.spanienakanapie.secondaryContainerDarkMediumContrast
import com.example.spanienakanapie.secondaryContainerLight
import com.example.spanienakanapie.secondaryContainerLightHighContrast
import com.example.spanienakanapie.secondaryContainerLightMediumContrast
import com.example.spanienakanapie.secondaryDark
import com.example.spanienakanapie.secondaryDarkHighContrast
import com.example.spanienakanapie.secondaryDarkMediumContrast
import com.example.spanienakanapie.secondaryLight
import com.example.spanienakanapie.secondaryLightHighContrast
import com.example.spanienakanapie.secondaryLightMediumContrast
import com.example.spanienakanapie.surfaceBrightDark
import com.example.spanienakanapie.surfaceBrightDarkHighContrast
import com.example.spanienakanapie.surfaceBrightDarkMediumContrast
import com.example.spanienakanapie.surfaceBrightLight
import com.example.spanienakanapie.surfaceBrightLightHighContrast
import com.example.spanienakanapie.surfaceBrightLightMediumContrast
import com.example.spanienakanapie.surfaceContainerDark
import com.example.spanienakanapie.surfaceContainerDarkHighContrast
import com.example.spanienakanapie.surfaceContainerDarkMediumContrast
import com.example.spanienakanapie.surfaceContainerHighDark
import com.example.spanienakanapie.surfaceContainerHighDarkHighContrast
import com.example.spanienakanapie.surfaceContainerHighDarkMediumContrast
import com.example.spanienakanapie.surfaceContainerHighLight
import com.example.spanienakanapie.surfaceContainerHighLightHighContrast
import com.example.spanienakanapie.surfaceContainerHighLightMediumContrast
import com.example.spanienakanapie.surfaceContainerHighestDark
import com.example.spanienakanapie.surfaceContainerHighestDarkHighContrast
import com.example.spanienakanapie.surfaceContainerHighestDarkMediumContrast
import com.example.spanienakanapie.surfaceContainerHighestLight
import com.example.spanienakanapie.surfaceContainerHighestLightHighContrast
import com.example.spanienakanapie.surfaceContainerHighestLightMediumContrast
import com.example.spanienakanapie.surfaceContainerLight
import com.example.spanienakanapie.surfaceContainerLightHighContrast
import com.example.spanienakanapie.surfaceContainerLightMediumContrast
import com.example.spanienakanapie.surfaceContainerLowDark
import com.example.spanienakanapie.surfaceContainerLowDarkHighContrast
import com.example.spanienakanapie.surfaceContainerLowDarkMediumContrast
import com.example.spanienakanapie.surfaceContainerLowLight
import com.example.spanienakanapie.surfaceContainerLowLightHighContrast
import com.example.spanienakanapie.surfaceContainerLowLightMediumContrast
import com.example.spanienakanapie.surfaceContainerLowestDark
import com.example.spanienakanapie.surfaceContainerLowestDarkHighContrast
import com.example.spanienakanapie.surfaceContainerLowestDarkMediumContrast
import com.example.spanienakanapie.surfaceContainerLowestLight
import com.example.spanienakanapie.surfaceContainerLowestLightHighContrast
import com.example.spanienakanapie.surfaceContainerLowestLightMediumContrast
import com.example.spanienakanapie.surfaceDark
import com.example.spanienakanapie.surfaceDarkHighContrast
import com.example.spanienakanapie.surfaceDarkMediumContrast
import com.example.spanienakanapie.surfaceDimDark
import com.example.spanienakanapie.surfaceDimDarkHighContrast
import com.example.spanienakanapie.surfaceDimDarkMediumContrast
import com.example.spanienakanapie.surfaceDimLight
import com.example.spanienakanapie.surfaceDimLightHighContrast
import com.example.spanienakanapie.surfaceDimLightMediumContrast
import com.example.spanienakanapie.surfaceLight
import com.example.spanienakanapie.surfaceLightHighContrast
import com.example.spanienakanapie.surfaceLightMediumContrast
import com.example.spanienakanapie.surfaceVariantDark
import com.example.spanienakanapie.surfaceVariantDarkHighContrast
import com.example.spanienakanapie.surfaceVariantDarkMediumContrast
import com.example.spanienakanapie.surfaceVariantLight
import com.example.spanienakanapie.surfaceVariantLightHighContrast
import com.example.spanienakanapie.surfaceVariantLightMediumContrast
import com.example.spanienakanapie.tertiaryContainerDark
import com.example.spanienakanapie.tertiaryContainerDarkHighContrast
import com.example.spanienakanapie.tertiaryContainerDarkMediumContrast
import com.example.spanienakanapie.tertiaryContainerLight
import com.example.spanienakanapie.tertiaryContainerLightHighContrast
import com.example.spanienakanapie.tertiaryContainerLightMediumContrast
import com.example.spanienakanapie.tertiaryDark
import com.example.spanienakanapie.tertiaryDarkHighContrast
import com.example.spanienakanapie.tertiaryDarkMediumContrast
import com.example.spanienakanapie.tertiaryLight
import com.example.spanienakanapie.tertiaryLightHighContrast
import com.example.spanienakanapie.tertiaryLightMediumContrast

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
//    surfaceDim = surfaceDimLight,
//    surfaceBright = surfaceBrightLight,
//    surfaceContainerLowest = surfaceContainerLowestLight,
//    surfaceContainerLow = surfaceContainerLowLight,
//    surfaceContainer = surfaceContainerLight,
//    surfaceContainerHigh = surfaceContainerHighLight,
//    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
//    surfaceDim = surfaceDimDark,
//    surfaceBright = surfaceBrightDark,
//    surfaceContainerLowest = surfaceContainerLowestDark,
//    surfaceContainerLow = surfaceContainerLowDark,
//    surfaceContainer = surfaceContainerDark,
//    surfaceContainerHigh = surfaceContainerHighDark,
//    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
//    surfaceDim = surfaceDimLightMediumContrast,
//    surfaceBright = surfaceBrightLightMediumContrast,
//    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
//    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
//    surfaceContainer = surfaceContainerLightMediumContrast,
//    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
//    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
//    surfaceDim = surfaceDimLightHighContrast,
//    surfaceBright = surfaceBrightLightHighContrast,
//    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
//    surfaceContainerLow = surfaceContainerLowLightHighContrast,
//    surfaceContainer = surfaceContainerLightHighContrast,
//    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
//    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
//    surfaceDim = surfaceDimDarkMediumContrast,
//    surfaceBright = surfaceBrightDarkMediumContrast,
//    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
//    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
//    surfaceContainer = surfaceContainerDarkMediumContrast,
//    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
//    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
//    surfaceDim = surfaceDimDarkHighContrast,
//    surfaceBright = surfaceBrightDarkHighContrast,
//    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
//    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
//    surfaceContainer = surfaceContainerDarkHighContrast,
//    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
//    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun AppTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}