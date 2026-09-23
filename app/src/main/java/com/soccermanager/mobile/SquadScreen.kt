package com.soccermanager.mobile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SquadScreen(game: GameViewModel, padding: PaddingValues) {
    ScreenColumn(padding) {
        ScreenHeader("Team room", "Squad & Tactics")
        SurfaceCard {
            SectionLabel("Starting XI", "${game.startersCount}/11")
            PitchRow(game.players.filter { it.starting && it.position == "GK" }, game)
            Spacer(Modifier.height(12.dp))
            PitchRow(game.players.filter { it.starting && listOf("CB", "LB", "RB").contains(it.position) }, game)
            Spacer(Modifier.height(12.dp))
            PitchRow(game.players.filter { it.starting && listOf("CM", "CAM").contains(it.position) }, game)
            Spacer(Modifier.height(12.dp))
            PitchRow(game.players.filter { it.starting && listOf("LW", "ST", "RW").contains(it.position) }, game)
        }
        Spacer(Modifier.height(18.dp))
        SectionLabel("Match plan")
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Tactic.entries.forEach { tactic ->
                TacticOption(tactic, game.tactic == tactic) { game.selectTactic(tactic) }
            }
        }
        Spacer(Modifier.height(18.dp))
        SurfaceCard {
            SectionLabel("Bench", "Tap to swap")
            game.players.filter { !it.starting }.forEach { player ->
                PlayerListRow(player) { game.togglePlayer(player.id) }
            }
        }
        Spacer(Modifier.height(18.dp))
        Text("Tap a starter to move them to the bench. Select a bench player to bring them in.", color = SoccerPalette.muted, fontSize = 12.sp, modifier = Modifier.padding(horizontal = 4.dp))
    }
}

@Composable
private fun PitchRow(players: List<Player>, game: GameViewModel) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        players.forEach { PlayerBadge(it) { game.togglePlayer(it.id) } }
    }
}

@Composable
private fun PlayerListRow(player: Player, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().height(52.dp).padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(player.name, color = SoccerPalette.text, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text("${player.position}  •  ${player.stamina}% stamina", color = SoccerPalette.muted, fontSize = 11.sp)
        }
        androidx.compose.material3.TextButton(onClick = onClick) { Text("Select", color = SoccerPalette.ember, fontWeight = FontWeight.Bold) }
    }
}