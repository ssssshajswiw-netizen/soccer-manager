package com.soccermanager.mobile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Code
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BuildCenterScreen(game: GameViewModel, padding: PaddingValues) {
    ScreenColumn(padding) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { game.navigate(AppScreen.DASHBOARD) }, modifier = Modifier.size(48.dp)) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = SoccerPalette.text)
            }
            Text("Build Center", color = SoccerPalette.text, fontSize = 26.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 4.dp))
        }
        Spacer(Modifier.height(22.dp))
        SurfaceCard {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Icon(Icons.Default.CheckCircle, contentDescription = "Ready", tint = SoccerPalette.success, modifier = Modifier.size(30.dp))
                androidx.compose.foundation.layout.Column {
                    Text("WORKFLOW READY", color = SoccerPalette.success, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                    Text("soccer-manager-mobile", color = SoccerPalette.text, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(Modifier.height(18.dp))
            Text("Every push starts a clean JDK 21 + Gradle 8.10.2 Android build.", color = SoccerPalette.textSecondary, fontSize = 14.sp, lineHeight = 20.sp)
        }
        Spacer(Modifier.height(16.dp))
        SurfaceCard {
            SectionLabel("Latest workflow")
            BuildStep("01", "Checkout source", "Complete", true)
            BuildStep("02", "Compile debug APK", "Ready on push", true)
            BuildStep("03", "Upload artifact", "soccer-manager-debug-apk", true)
        }
        Spacer(Modifier.height(16.dp))
        SurfaceCard {
            Icon(Icons.Default.Code, contentDescription = "Actions", tint = SoccerPalette.ember, modifier = Modifier.size(28.dp))
            Spacer(Modifier.height(10.dp))
            Text("Download from GitHub Actions", color = SoccerPalette.text, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(6.dp))
            Text("Open the successful workflow run and download the artifact named soccer-manager-debug-apk. Install app-debug.apk on your Android phone.", color = SoccerPalette.muted, fontSize = 13.sp, lineHeight = 19.sp)
            Spacer(Modifier.height(14.dp))
            Button(
                onClick = { game.navigate(AppScreen.DASHBOARD) },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SoccerPalette.elevated)
            ) {
                Icon(Icons.Default.CloudDownload, contentDescription = null, tint = SoccerPalette.text)
                Spacer(Modifier.width(8.dp))
                Text("Back to club", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun BuildStep(number: String, title: String, status: String, complete: Boolean) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 9.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(number, color = SoccerPalette.ember, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(32.dp))
        Text(title, color = SoccerPalette.textSecondary, fontSize = 13.sp, modifier = Modifier.weight(1f))
        Text(status, color = if (complete) SoccerPalette.success else SoccerPalette.warning, fontSize = 11.sp, fontWeight = FontWeight.Bold)
    }
}