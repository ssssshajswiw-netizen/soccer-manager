package com.soccermanager.mobile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScreenColumn(padding: PaddingValues, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {
        content()
    }
}

@Composable
fun ScreenHeader(eyebrow: String, title: String, onBuild: (() -> Unit)? = null) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column {
            Text(eyebrow.uppercase(), color = SoccerPalette.ember, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.3.sp)
            Spacer(Modifier.height(4.dp))
            Text(title, color = SoccerPalette.text, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }
        if (onBuild != null) {
            IconButton(onClick = onBuild, modifier = Modifier.size(48.dp)) {
                Icon(Icons.Default.Build, contentDescription = "Open build center", tint = SoccerPalette.textSecondary)
            }
        }
    }
    Spacer(Modifier.height(22.dp))
}

@Composable
fun SurfaceCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(SoccerPalette.card)
            .border(1.dp, SoccerPalette.divider, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        content()
    }
}

@Composable
fun SectionLabel(text: String, trailing: String? = null) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text.uppercase(), color = SoccerPalette.muted, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        if (trailing != null) Text(trailing, color = SoccerPalette.ember, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
    Spacer(Modifier.height(10.dp))
}

@Composable
fun StatBlock(value: String, label: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, color = SoccerPalette.text, fontSize = 21.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(3.dp))
        Text(label, color = SoccerPalette.muted, fontSize = 11.sp)
    }
}

@Composable
fun FormDot(value: String) {
    val color = when (value) {
        "W" -> SoccerPalette.success
        "D" -> SoccerPalette.warning
        else -> SoccerColors.error
    }
    Box(
        modifier = Modifier.size(24.dp).clip(CircleShape).background(color),
        contentAlignment = Alignment.Center
    ) { Text(value, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold) }
}

@Composable
fun TacticOption(tactic: Tactic, selected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(104.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) SoccerPalette.ember.copy(alpha = 0.18f) else SoccerPalette.elevated)
            .border(1.dp, if (selected) SoccerPalette.ember else SoccerPalette.divider, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(12.dp)
    ) {
        Text(tactic.title, color = if (selected) SoccerPalette.ember else SoccerPalette.text, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        Spacer(Modifier.height(5.dp))
        Text(tactic.detail, color = SoccerPalette.muted, fontSize = 10.sp, lineHeight = 13.sp)
    }
}

@Composable
fun PlayerBadge(player: Player, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(72.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(SoccerPalette.ember)
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp, horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.size(27.dp).clip(CircleShape).background(Color.White), contentAlignment = Alignment.Center) {
            Text(player.position.take(1), color = SoccerPalette.ember, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(4.dp))
        Text(player.name.substringBefore(" "), color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Text("${player.rating} OVR", color = Color.White.copy(alpha = 0.8f), fontSize = 9.sp)
    }
}