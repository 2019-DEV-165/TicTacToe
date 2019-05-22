package com.katatictactoe

import androidx.lifecycle.ViewModel

class TicTacToeViewModel : ViewModel() {

    private var mCurrentPlayer = Player.PLAYER_X_ID
    private var mGameBoard = Array(3) { IntArray(3) }

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
        }
    }

    fun getGameBoardByIndex(position: Int) = getGameBoard()[position / 3][position % 3]

    fun resetGameBoard() {
        mCurrentPlayer = Player.PLAYER_X_ID
        mGameBoard = Array(3) { IntArray(3) }
    }

}