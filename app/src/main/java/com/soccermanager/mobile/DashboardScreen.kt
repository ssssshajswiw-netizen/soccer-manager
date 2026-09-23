package com.soccermanager.mobile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.PaddingValues

@Composable
fun DashboardScreen(game: GameViewModel, padding: PaddingValues) {
    ScreenColumn(padding) {
        ScreenHeader("Northbridge FC", "Matchday 9") { game.navigate(AppScreen.BUILD) }
        SurfaceCard {
            SectionLabel("Next fixture", "SAT 15:00")
            Text("Riverside United", color = SoccerPalette.text, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("Ember Stadium  •  Home", color = SoccerPalette.muted, fontSize = 13.sp)
            Spacer(Modifier.height(18.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Your squad", color = SoccerPalette.muted, fontSize = 12.sp)
                    Text("${game.averageRating} OVR", color = SoccerPalette.text, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { game.navigate(AppScreen.MATCH) },
                    colors = ButtonDefaults.buttonColors(containerColor = SoccerPalette.ember),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 11.dp)
                ) { Text("Open match", color = Color.White, fontWeight = FontWeight.Bold) }
            }
        }
        Spacer(Modifier.height(18.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SurfaceCard(Modifier.weight(1f)) { StatBlock("1st", "League position") }
            SurfaceCard(Modifier.weight(1f)) { StatBlock("19", "Points") }
            SurfaceCard(Modifier.weight(1f)) { StatBlock("+8", "Goal diff") }
        }
        Spacer(Modifier.height(18.dp))
        SurfaceCard {
            SectionLabel("Club pulse", "GOOD")
            Text("Squad morale", color = SoccerPalette.textSecondary, fontSize = 14.sp)
            Spacer(Modifier.height(8.dp))
            androidx.compose.material3.LinearProgressIndicator(
                progress = { 0.78f },
                modifier = Modifier.fillMaxWidth().height(8.dp),
                color = SoccerPalette.success,
                trackColor = SoccerPalette.elevated
            )
            Spacer(Modifier.height(9.dp))
            Text("78%  •  The dressing room is ready for the derby.", color = SoccerPalette.muted, fontSize = 12.sp)
        }
        Spacer(Modifier.height(18.dp))
        SurfaceCard {
            SectionLabel("Quick tactics")
            Text("${game.tactic.title} approach", color = SoccerPalette.text, fontSize = 17.sp, fontWeight = FontWeight.Bold)
            Text(game.tactic.detail, color = SoccerPalette.muted, fontSize = 13.sp)
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = { game.navigate(AppScreen.SQUAD) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = SoccerPalette.elevated)
            ) { Text("Manage squad & tactics", color = SoccerPalette.text, fontWeight = FontWeight.Bold) }
        }
        Spacer(Modifier.height(12.dp))
    }
}