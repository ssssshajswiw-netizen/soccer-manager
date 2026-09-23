package com.soccermanager.mobile

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Obsidian = Color(0xFF121619)
private val Graphite = Color(0xFF1A2024)
private val Slate = Color(0xFF252D33)
private val Ember = Color(0xFFE05A30)
private val EmberDark = Color(0xFFC44820)
private val TextPrimary = Color(0xFFF4F6F8)
private val TextSecondary = Color(0xFFD0D7DE)
private val Muted = Color(0xFF8C969E)
private val Divider = Color(0xFF2D3748)
private val Success = Color(0xFF22C55E)

val SoccerColors = darkColorScheme(
    primary = Ember,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF2A1D18),
    onPrimaryContainer = Color(0xFFF08050),
    secondary = Graphite,
    onSecondary = TextSecondary,
    background = Obsidian,
    onBackground = TextPrimary,
    surface = Graphite,
    onSurface = TextPrimary,
    surfaceVariant = Slate,
    onSurfaceVariant = TextSecondary,
    outline = Divider,
    error = Color(0xFFEF4444),
    onError = Color.White
)

object SoccerPalette {
    val surface = Obsidian
    val card = Graphite
    val elevated = Slate
    val ember = Ember
    val emberDark = EmberDark
    val text = TextPrimary
    val textSecondary = TextSecondary
    val muted = Muted
    val divider = Divider
    val success = Success
    val warning = Color(0xFFF59E0B)
    val info = Color(0xFF3B82F6)
}

@Composable
fun SoccerManagerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = SoccerColors,
        content = content
    )
}