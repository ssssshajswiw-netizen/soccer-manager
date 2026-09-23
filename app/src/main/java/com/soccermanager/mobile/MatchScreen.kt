package com.soccermanager.mobile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MatchScreen(game: GameViewModel, padding: PaddingValues) {
    ScreenColumn(padding) {
        ScreenHeader("Match engine", "Live Match")
        SurfaceCard {
            SectionLabel(if (game.matchInProgress) "Live now" else "Match centre", "${game.matchMinute}'")
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                ScoreTeam("NORTHBRIDGE", game.homeGoals)
                Text("—", color = SoccerPalette.muted, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                ScoreTeam("RIVERSIDE", game.awayGoals)
            }
            Spacer(Modifier.height(18.dp))
            LinearProgressIndicator(
                progress = { game.matchMinute / 90f },
                modifier = Modifier.fillMaxWidth().height(8.dp),
                color = SoccerPalette.ember,
                trackColor = SoccerPalette.elevated
            )
            Spacer(Modifier.height(8.dp))
            Text("Possession  ${game.possession}%  /  ${100 - game.possession}%", color = SoccerPalette.muted, fontSize = 12.sp)
        }
        Spacer(Modifier.height(16.dp))
        if (!game.matchInProgress) {
            Button(
                onClick = { game.startMatch() },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = SoccerPalette.ember)
            ) { Text(if (game.matchMinute == 90) "Play rematch" else "Start match", color = Color.White, fontWeight = FontWeight.Bold) }
        } else {
            Text("SIMULATING  •  Your ${game.tactic.title.lowercase()} plan is active", color = SoccerPalette.ember, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(20.dp))
        SurfaceCard {
            SectionLabel("Timeline", "${game.events.size} events")
            if (game.events.isEmpty()) {
                Text("No match in progress. Start the match to see the story unfold.", color = SoccerPalette.muted, fontSize = 14.sp)
            } else {
                game.events.asReversed().forEach { event ->
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 7.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("${event.minute}'", color = SoccerPalette.ember, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(32.dp))
                        Text(event.text, color = if (event.positive) SoccerPalette.textSecondary else SoccerColors.error, fontSize = 13.sp)
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Text("You can leave this screen while the match runs. The simulation continues in the background.", color = SoccerPalette.muted, fontSize = 12.sp)
    }
}

@Composable
private fun ScoreTeam(name: String, score: Int) {
    Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
        Text(name, color = SoccerPalette.muted, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        Text(score.toString(), color = SoccerPalette.text, fontSize = 40.sp, fontWeight = FontWeight.Bold)
    }
}