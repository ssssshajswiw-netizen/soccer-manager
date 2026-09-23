package com.soccermanager.mobile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LeagueScreen(game: GameViewModel, padding: PaddingValues) {
    ScreenColumn(padding) {
        ScreenHeader("Season 1  •  Week 8", "League Table")
        SurfaceCard {
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("#", color = SoccerPalette.muted, fontSize = 11.sp, modifier = Modifier.width(28.dp))
                Text("CLUB", color = SoccerPalette.muted, fontSize = 11.sp, modifier = Modifier.weight(1f))
                Text("P", color = SoccerPalette.muted, fontSize = 11.sp, modifier = Modifier.width(32.dp))
                Text("GD", color = SoccerPalette.muted, fontSize = 11.sp, modifier = Modifier.width(38.dp))
                Text("PTS", color = SoccerPalette.muted, fontSize = 11.sp, modifier = Modifier.width(38.dp))
            }
            game.league.forEachIndexed { index, team ->
                LeagueRow(index + 1, team)
            }
        }
        Spacer(Modifier.height(18.dp))
        SurfaceCard {
            SectionLabel("Next fixtures")
            FixtureRow("09", "Northbridge FC", "Riverside United", "HOME")
            FixtureRow("10", "Meadow Park", "Northbridge FC", "AWAY")
            FixtureRow("11", "Northbridge FC", "Old Town Rovers", "HOME")
        }
        Spacer(Modifier.height(16.dp))
        Text("Win: 3 pts  •  Draw: 1 pt  •  Goal difference breaks ties", color = SoccerPalette.muted, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 4.dp))
    }
}

@Composable
private fun LeagueRow(position: Int, team: LeagueTeam) {
    val isUs = team.name == "Northbridge FC"
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(position.toString(), color = if (isUs) SoccerPalette.ember else SoccerPalette.muted, fontSize = 13.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(28.dp))
        Text(team.name, color = if (isUs) SoccerPalette.text else SoccerPalette.textSecondary, fontSize = 13.sp, fontWeight = if (isUs) FontWeight.Bold else FontWeight.Normal, modifier = Modifier.weight(1f))
        Text(team.played.toString(), color = SoccerPalette.muted, fontSize = 13.sp, modifier = Modifier.width(32.dp))
        Text(if (team.goalDifference >= 0) "+${team.goalDifference}" else team.goalDifference.toString(), color = SoccerPalette.textSecondary, fontSize = 13.sp, modifier = Modifier.width(38.dp))
        Text(team.points.toString(), color = SoccerPalette.text, fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(38.dp))
    }
}

@Composable
private fun FixtureRow(week: String, home: String, away: String, venue: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 9.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(week, color = SoccerPalette.ember, fontWeight = FontWeight.Bold, fontSize = 13.sp, modifier = Modifier.width(32.dp))
        Text("$home  v  $away", color = SoccerPalette.textSecondary, fontSize = 13.sp, modifier = Modifier.weight(1f))
        Text(venue, color = SoccerPalette.muted, fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
}