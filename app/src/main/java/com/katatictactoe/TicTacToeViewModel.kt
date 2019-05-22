package com.katatictactoe

import androidx.lifecycle.ViewModel

class TicTacToeViewModel : ViewModel() {

    private var mCurrentPlayer = Player.PLAYER_X_ID
    private var mGameBoard = Array(3) { IntArray(3) }
    private var mMatchSummary: MatchSummary = MatchSummary()

    private fun getGameBoard(): Array<IntArray> {
        return mGameBoard
    }

    private fun isIndexAvailableForPlayerMove(index: Int) = getGameBoardByIndex(index) == 0

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
        if (isIndexAvailableForPlayerMove(index)) {
            updateGameBoardIndex(index, getCurrentPlayer())
            updateCurrentPlayer(getCurrentPlayer())
            updateMatchSummary()
        }
    }

    fun getGameBoardByIndex(position: Int) = getGameBoard()[position / 3][position % 3]

    fun resetGameBoard() {
        mCurrentPlayer = Player.PLAYER_X_ID
        mGameBoard = Array(3) { IntArray(3) }
    }


    private fun validateWinnerByRow() {
        IntRange(0, 2).forEach { index ->
            when {
                checkIndexIsNotEmpty(index, 0)
                        && compareIndices(Pair(index, 0), Pair(index, 1), Pair(index, 2))
                -> {
                    mMatchSummary = MatchSummary(matchStatus = MatchStatus.WIN_BY_ROW)
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
                    mMatchSummary = MatchSummary(
                        matchStatus = MatchStatus.WIN_BY_COLUMN
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
    }

}