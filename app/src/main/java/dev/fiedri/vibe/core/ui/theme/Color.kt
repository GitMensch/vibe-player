package dev.fiedri.vibe.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class VibeColors(
    val background: Color,
    val foreground: Color,
    val cards: Color,
    val popover: Color,
    val primary: Color,
    val secondary: Color,
    val muted: Color,
    val mutedForeground: Color,
    val destructive: Color,
    val border: Color,
    val input: Color,
    val ring: Color,
    val primaryForeground: Color,
    val secondaryForeground: Color,
    val destructiveForeground: Color
) {
    val accent: Color = primary
}

val VibeDarkColors = VibeColors(
    background= Color(0xFF000000),
    foreground= Color(0xFFf9fbfb),
    cards = Color(0xFF1a1c1c),
    popover= Color(0xFF2b3033),
    primary = Color(0xFF193cb8),
    secondary= Color(0xFF39393e),
    muted= Color(0xFF373f43),
    mutedForeground= Color(0xFFaab4b9),
    destructive= Color(0xFFff6467),
    border= Color.White.copy(alpha = 0.1f),
    input = Color.White.copy(alpha = 0.15f),
    ring = Color(0xFF758a94),
    primaryForeground    = Color(0xFFF8FAFC),
    secondaryForeground  = Color(0xFFF8FAFC),
    destructiveForeground = Color(0xFF000000)
)

val VibeLightColors = VibeColors(
    background = Color(0xFFF8FAFC),
    foreground = Color(0xFF0F172A),
    cards = Color(0xFFFFFFFF),
    popover = Color(0xFFFFFFFF),
    primary = Color(0xFF4F46E5),
    secondary = Color(0xFFF1F5F9),
    muted = Color(0xFFE2E8F0),
    mutedForeground = Color(0xFF64748B),
    destructive = Color(0xFFEF4444),
    border = Color(0xFFE2E8F0),
    input = Color(0xFFEEEEEE),
    ring = Color(0xFF4F46E5),
    primaryForeground    = Color(0xFFFFFFFF),
    secondaryForeground  = Color(0xFF0F172A),
    destructiveForeground = Color(0xFFFFFFFF)
)
