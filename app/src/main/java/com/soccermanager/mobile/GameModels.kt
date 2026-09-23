package com.soccermanager.mobile

enum class AppScreen(val title: String) {
    DASHBOARD("Dashboard"),
    SQUAD("Squad & Tactics"),
    MATCH("Live Match"),
    LEAGUE("League"),
    BUILD("Build Center")
}

enum class Tactic(val title: String, val detail: String) {
    DEFENSIVE("Defensive", "Compact block and counter attacks"),
    BALANCED("Balanced", "Keep shape and control the tempo"),
    ATTACKING("Attacking", "Push numbers forward for goals")
}

data class Player(
    val id: Int,
    val name: String,
    val position: String,
    val rating: Int,
    val stamina: Int,
    val starting: Boolean
)

data class MatchEvent(val minute: Int, val text: String, val positive: Boolean)

data class LeagueTeam(
    val name: String,
    val played: Int,
    val points: Int,
    val goalDifference: Int,
    val form: List<String>
)

fun initialPlayers() = listOf(
    Player(1, "M. Costa", "GK", 78, 92, true),
    Player(2, "J. Silva", "CB", 76, 88, true),
    Player(3, "R. Novak", "CB", 74, 84, true),
    Player(4, "A. King", "LB", 73, 90, true),
    Player(5, "L. Chen", "RB", 72, 87, true),
    Player(6, "D. Okafor", "CM", 80, 95, true),
    Player(7, "T. Moretti", "CM", 77, 91, true),
    Player(8, "S. Ito", "CAM", 82, 89, true),
    Player(9, "E. Mensah", "LW", 79, 86, true),
    Player(10, "K. Adeyemi", "ST", 84, 93, true),
    Player(11, "P. Walsh", "RW", 81, 90, true),
    Player(12, "N. Rossi", "GK", 70, 96, false),
    Player(13, "B. Taylor", "CB", 71, 88, false),
    Player(14, "Y. Park", "CM", 75, 92, false),
    Player(15, "F. Laurent", "ST", 76, 85, false)
)

fun initialLeague() = listOf(
    LeagueTeam("Northbridge FC", 8, 19, 8, listOf("W", "W", "D", "W", "W")),
    LeagueTeam("Riverside United", 8, 17, 5, listOf("W", "D", "W", "W", "L")),
    LeagueTeam("Ashford Athletic", 8, 16, 4, listOf("W", "W", "L", "W", "D")),
    LeagueTeam("Kingsport City", 8, 13, 1, listOf("D", "W", "L", "W", "D")),
    LeagueTeam("Old Town Rovers", 8, 11, -2, listOf("L", "D", "W", "L", "W")),
    LeagueTeam("Meadow Park", 8, 8, -7, listOf("L", "L", "D", "W", "L"))
)