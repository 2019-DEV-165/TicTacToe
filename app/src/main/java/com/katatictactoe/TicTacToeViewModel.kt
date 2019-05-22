package com.katatictactoe

import androidx.lifecycle.ViewModel

class TicTacToeViewModel : ViewModel() {

    private var mCurrentPlayer = Player.PLAYER_X_ID
    private var mGameBoard = Array(3) { IntArray(3) }
    private var mMatchSummary: MatchSummary = MatchSummary()
    private var isValidMove = true
    var isGameFinished: Boolean = false

    private fun getGameBoard(): Array<IntArray> {
        return mGameBoard
    }

    private fun isIndexAvailableForPlayerMove(index: Int) = getGameBoardByIndex(index) == 0

    private fun isGameNotFinishedOrMoveAvailable() = !isGameFinished && GAME_MOVE_COUNTER <= MAX_MOVE_COUNTER

    private fun updateGameBoardIndex(position: Int, playerTag: Int) {
        getGameBoard()[position / 3][position % 3] = playerTag
    }

    private fun updateCurrentPlayer(playerTag: Int) {
        mCurrentPlayer = when (playerTag) {
            Player.PLAYER_X_ID -> Player.PLAYER_O_ID
            else -> Player.PLAYER_X_ID
        }
    }


    fun getCurrentPlayer(): Int {
        return mCurrentPlayer
    }

    fun storePlayerMoves(@GameBoardPosition.Position index: Int) {
        when {
            isGameNotFinishedOrMoveAvailable() -> when {
                isIndexAvailableForPlayerMove(index) -> {
                    updateGameBoardIndex(index, getCurrentPlayer())
                    updateCurrentPlayer(getCurrentPlayer())
                    GAME_MOVE_COUNTER = GAME_MOVE_COUNTER.plus(1)
                    isValidMove = true
                    mMatchSummary = MatchSummary(isValidMove = isValidMove)
                    updateMatchSummary()
                }
            }
            else -> {
                isValidMove = false
                mMatchSummary = MatchSummary(
                    matchStatus = MatchStatus.MATCH_END,
                    isValidMove = isValidMove
                )
            }
        }
    }

    fun getGameBoardByIndex(position: Int) = getGameBoard()[position / 3][position % 3]

    fun resetGameBoard() {
        GAME_MOVE_COUNTER = 0
        mCurrentPlayer = Player.PLAYER_X_ID
        mGameBoard = Array(3) { IntArray(3) }
        isGameFinished = false
    }

    private fun validateWinnerByRow() {
        IntRange(0, 2).forEach { index ->
            when {
                checkIndexIsNotEmpty(index, 0)
                        && compareIndices(Pair(index, 0), Pair(index, 1), Pair(index, 2))
                -> {
                    isGameFinished = true
                    mMatchSummary = MatchSummary(
                        matchStatus = MatchStatus.WIN_BY_ROW,
                        isValidMove = isValidMove
                    )
                }
            }
        }
    }

    private fun validateWinnerByColumn() {
        IntRange(0, 2).forEach { index ->
            when {
                checkIndexIsNotEmpty(0, index) &&
                        compareIndices(Pair(0, index), Pair(1, index), Pair(2, index))
                -> {
                    isGameFinished = true
                    mMatchSummary = MatchSummary(
                        matchStatus = MatchStatus.WIN_BY_COLUMN,
                        isValidMove = isValidMove
                    )
                }
            }
        }
    }

    private fun validateWinnerByDiagonal() {
        when {
            checkIndexIsNotEmpty(0, 0) && compareIndices(Pair(0, 0), Pair(1, 1), Pair(2, 2)) ||
                    checkIndexIsNotEmpty(0, 2) && compareIndices(Pair(0, 2), Pair(1, 1), Pair(2, 0))
            -> {
                isGameFinished = true
                mMatchSummary = MatchSummary(
                    matchStatus = MatchStatus.WIN_BY_DIAGONAL,
                    isValidMove = isValidMove
                )
            }
        }
    }

    private fun validateMatchDrawn() {
        when (GAME_MOVE_COUNTER) {
            MAX_MOVE_COUNTER -> {
                isGameFinished = true
                when {
                    mMatchSummary.matchStatus != MatchStatus.WIN_BY_ROW &&
                            mMatchSummary.matchStatus != MatchStatus.WIN_BY_COLUMN &&
                            mMatchSummary.matchStatus != MatchStatus.WIN_BY_DIAGONAL
                    -> mMatchSummary = MatchSummary(
                        matchStatus = MatchStatus.DRAW,
                        isValidMove = isValidMove
                    )
                }
            }
        }
    }

    private fun checkIndexIsNotEmpty(firstIndex: Int, secondIndex: Int) = getGameBoard()[firstIndex][secondIndex] > 0

    private fun compareIndices(
        firstPosition: Pair<Int, Int>,
        secondPosition: Pair<Int, Int>,
        thirdPosition: Pair<Int, Int>
    ): Boolean {

        val firstIndexValue = getGameBoard()[firstPosition.first][firstPosition.second]
        val secondIndexValue = getGameBoard()[secondPosition.first][secondPosition.second]
        val thirdIndexValue = getGameBoard()[thirdPosition.first][thirdPosition.second]

        return firstIndexValue == secondIndexValue &&
                firstIndexValue == thirdIndexValue
    }

    fun getMatchSummary(): MatchSummary {
        return mMatchSummary
    }

    private fun updateMatchSummary() {
        validateWinnerByRow()
        validateWinnerByColumn()
        validateWinnerByDiagonal()
        validateMatchDrawn()
    }

    companion object {
        private var GAME_MOVE_COUNTER = 0
        private var MAX_MOVE_COUNTER = 9
    }


}