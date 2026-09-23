package com.soccermanager.mobile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel : ViewModel() {
    var selectedScreen by mutableStateOf(AppScreen.DASHBOARD)
        private set
    var tactic by mutableStateOf(Tactic.BALANCED)
        private set
    val players = mutableStateListOf<Player>().apply { addAll(initialPlayers()) }
    val league = initialLeague()
    val events = mutableStateListOf<MatchEvent>()
    var matchMinute by mutableIntStateOf(0)
        private set
    var homeGoals by mutableIntStateOf(0)
        private set
    var awayGoals by mutableIntStateOf(0)
        private set
    var possession by mutableIntStateOf(52)
        private set
    var matchInProgress by mutableStateOf(false)
        private set
    private var simulationJob: Job? = null

    val startersCount: Int get() = players.count { it.starting }
    val averageRating: Int
        get() = players.filter { it.starting }.map { it.rating }.average().toInt()

    fun navigate(screen: AppScreen) {
        selectedScreen = screen
    }

    fun setTactic(value: Tactic) {
        tactic = value
        possession = when (value) {
            Tactic.DEFENSIVE -> 47
            Tactic.BALANCED -> 52
            Tactic.ATTACKING -> 58
        }
    }

    fun togglePlayer(playerId: Int) {
        val index = players.indexOfFirst { it.id == playerId }
        if (index < 0) return
        val current = players[index]
        if (!current.starting && startersCount >= 11) return
        players[index] = current.copy(starting = !current.starting)
    }

    fun startMatch() {
        if (matchInProgress) return
        matchMinute = 0
        homeGoals = 0
        awayGoals = 0
        possession = when (tactic) {
            Tactic.DEFENSIVE -> 47
            Tactic.BALANCED -> 52
            Tactic.ATTACKING -> 58
        }
        events.clear()
        events.add(MatchEvent(0, "Kick-off at Ember Stadium. Make your call, boss.", true))
        matchInProgress = true
        simulationJob?.cancel()
        simulationJob = viewModelScope.launch {
            while (matchMinute < 90) {
                delay(650)
                matchMinute = (matchMinute + 5).coerceAtMost(90)
                possession = (possession + Random.nextInt(-3, 4)).coerceIn(38, 64)
                createEvent()
            }
            events.add(MatchEvent(90, "Full time. The referee calls it.", homeGoals >= awayGoals))
            matchInProgress = false
        }
    }

    fun resetMatch() {
        simulationJob?.cancel()
        matchInProgress = false
        matchMinute = 0
        homeGoals = 0
        awayGoals = 0
        events.clear()
    }

    private fun createEvent() {
        val roll = Random.nextInt(100)
        when {
            roll < 7 -> {
                homeGoals++
                events.add(MatchEvent(matchMinute, "GOAL! Adeyemi finishes a fast team move.", true))
            }
            roll < 12 -> {
                awayGoals++
                events.add(MatchEvent(matchMinute, "Riverside break through after a loose ball.", false))
            }
            roll < 28 -> events.add(MatchEvent(matchMinute, "Costa makes a sharp save to keep us level.", true))
            roll < 43 -> events.add(MatchEvent(matchMinute, "Midfield battle. Okafor wins the second ball.", true))
            roll < 56 -> events.add(MatchEvent(matchMinute, "Our press forces Riverside back toward their box.", true))
        }
    }
}