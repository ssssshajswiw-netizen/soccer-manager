package com.soccermanager.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SoccerManagerTheme { SoccerManagerApp() } }
    }
}

@Composable
fun SoccerManagerApp(game: GameViewModel = viewModel()) {
    val screen = game.selectedScreen
    val tabs = listOf(AppScreen.DASHBOARD, AppScreen.SQUAD, AppScreen.MATCH, AppScreen.LEAGUE)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = SoccerPalette.surface,
        bottomBar = {
            NavigationBar(containerColor = SoccerPalette.card) {
                tabs.forEach { tab ->
                    NavigationBarItem(
                        selected = screen == tab,
                        onClick = { game.navigate(tab) },
                        icon = { Icon(tabIcon(tab), contentDescription = tab.title) },
                        label = { Text(tab.title.substringBefore(" ")) }
                    )
                }
            }
        }
    ) { padding ->
        when (screen) {
            AppScreen.DASHBOARD -> DashboardScreen(game, padding)
            AppScreen.SQUAD -> SquadScreen(game, padding)
            AppScreen.MATCH -> MatchScreen(game, padding)
            AppScreen.LEAGUE -> LeagueScreen(game, padding)
            AppScreen.BUILD -> BuildCenterScreen(game, padding)
        }
    }
}

private fun tabIcon(screen: AppScreen) = when (screen) {
    AppScreen.DASHBOARD -> Icons.Default.Home
    AppScreen.SQUAD -> Icons.Default.Groups
    AppScreen.MATCH -> Icons.Default.SportsSoccer
    AppScreen.LEAGUE -> Icons.Default.CalendarMonth
    AppScreen.BUILD -> Icons.Default.Build
}