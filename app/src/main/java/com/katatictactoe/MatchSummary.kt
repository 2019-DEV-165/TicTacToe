package com.katatictactoe

data class MatchSummary(
    var matchStatus: MatchStatus? = null,
    var isValidMove: Boolean = true
)