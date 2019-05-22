package com.katatictactoe

data class MatchSummary(
    var matchStatus: MatchStatus? = null,
    var matchSummary: String = "",
    var isValidMove: Boolean = true
)