package br.com.luizrcs.correiostracker.ui.theme

import androidx.compose.ui.graphics.*

val md_theme_light_primary = CorreiosTrackerColors.Primary
val md_theme_light_onPrimary = Color.White
val md_theme_light_primaryContainer = CorreiosTrackerColors.Primary
val md_theme_light_onPrimaryContainer = Color.White
val md_theme_light_secondary = CorreiosTrackerColors.Secondary
val md_theme_light_onSecondary = Color.White
val md_theme_light_secondaryContainer = CorreiosTrackerColors.Secondary
val md_theme_light_onSecondaryContainer = Color.White
val md_theme_light_tertiary = CorreiosTrackerColors.Tertiary
val md_theme_light_onTertiary = CorreiosTrackerColors.OnTertiary
val md_theme_light_tertiaryContainer = CorreiosTrackerColors.Secondary
val md_theme_light_onTertiaryContainer = CorreiosTrackerColors.Tertiary
val md_theme_light_error = Color(0xFFb3261e)
val md_theme_light_errorContainer = Color(0xFFf9dedc)
val md_theme_light_onError = Color.White
val md_theme_light_onErrorContainer = Color(0xFF410e0b)
val md_theme_light_background = Color.White
val md_theme_light_onBackground = CorreiosTrackerColors.OnWhite
val md_theme_light_surface = Color.White
val md_theme_light_onSurface = CorreiosTrackerColors.OnWhite
val md_theme_light_surfaceVariant = Color.White
val md_theme_light_onSurfaceVariant = CorreiosTrackerColors.OnWhite
val md_theme_light_outline = CustomColors.Gray
val md_theme_light_inverseSurface = DarkThemeColors.Surface00dp
val md_theme_light_inverseOnSurface = DarkThemeColors.OnSurfaceHigh
val md_theme_light_inversePrimary = CorreiosTrackerColors.Primary

val md_theme_dark_primary = CorreiosTrackerColors.Primary
val md_theme_dark_onPrimary = DarkThemeColors.Surface00dp
val md_theme_dark_primaryContainer = CorreiosTrackerColors.Primary
val md_theme_dark_onPrimaryContainer = DarkThemeColors.Surface00dp
val md_theme_dark_secondary = DarkThemeColors.Surface01dp
val md_theme_dark_onSecondary = DarkThemeColors.OnSurfaceHigh
val md_theme_dark_secondaryContainer = DarkThemeColors.Surface03dp
val md_theme_dark_onSecondaryContainer = DarkThemeColors.OnSurfaceHigh
val md_theme_dark_tertiary = CorreiosTrackerColors.Tertiary
val md_theme_dark_onTertiary = CorreiosTrackerColors.OnTertiary
val md_theme_dark_tertiaryContainer = DarkThemeColors.Surface01dp
val md_theme_dark_onTertiaryContainer = DarkThemeColors.OnSurfaceMedium
val md_theme_dark_error = Color(0xFFf2b8b5)
val md_theme_dark_errorContainer = Color(0xFF8c1d18)
val md_theme_dark_onError = Color(0xFF601410)
val md_theme_dark_onErrorContainer = Color(0xFFf9dedc)
val md_theme_dark_background = DarkThemeColors.Surface00dp
val md_theme_dark_onBackground = DarkThemeColors.OnSurfaceHigh
val md_theme_dark_surface = DarkThemeColors.Surface00dp
val md_theme_dark_onSurface = DarkThemeColors.OnSurfaceHigh
val md_theme_dark_surfaceVariant = DarkThemeColors.Surface01dp
val md_theme_dark_onSurfaceVariant = DarkThemeColors.OnSurfaceHigh
val md_theme_dark_outline = CustomColors.Gray
val md_theme_dark_inverseSurface = Color.White
val md_theme_dark_inverseOnSurface = CorreiosTrackerColors.OnWhite
val md_theme_dark_inversePrimary = CorreiosTrackerColors.Primary

object CorreiosTrackerColors {
	val Primary = Color(0xFFffc107)
	val Secondary = Color(0xFF0745ff)
	val Tertiary = Color(0xFFc7c9ff)
	val OnTertiary = Color(0xFF00184a)
	val OnWhite = Color(0xFF1d1b16)
}

object DarkThemeColors {
	val Surface00dp = Color(0xFF121212)
	val Surface01dp = Color(0xFF1e1e1e)
	val Surface02dp = Color(0xFF222222)
	val Surface03dp = Color(0xFF242424)
	val Surface04dp = Color(0xFF272727)
	val Surface06dp = Color(0xFF2c2c2c)
	val Surface08dp = Color(0xFF2e2e2e)
	val Surface12dp = Color(0xFF333333)
	val Surface16dp = Color(0xFF343434)
	val Surface24dp = Color(0xFF383838)
	val OnSurfaceHigh = Color(0xFFdedede)
	val OnSurfaceMedium = Color(0xFF999999)
	val OnSurfaceDisabled = Color(0xFF616161)
}

object CustomColors {
	val Red = Color(0xFFf44336)
	val Pink = Color(0xFFe91e63)
	val Purple = Color(0xFF9c27b0)
	val Indigo = Color(0xFF3f51b5)
	val Blue = Color(0xFF2196f3)
	val Cyan = Color(0xFF00bcd4)
	val Teal = Color(0xFF009688)
	val Green = Color(0xFF4caf50)
	val Lime = Color(0xFFcddc39)
	val Yellow = Color(0xFFffeb3b)
	val Amber = Color(0xFFffc107)
	val Orange = Color(0xFFff9800)
	val Brown = Color(0xFF795548)
	val Gray = Color(0xFF9e9e9e)
}