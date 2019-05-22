package com.katatictactoe

import androidx.lifecycle.ViewModel

class TicTacToeViewModel : ViewModel() {

    private var mCurrentPlayer = Player.PLAYER_X_ID
    private var mGameBoard = Array(3) { IntArray(3) }

    fun getCurrentPlayer(): Int {
        return mCurrentPlayer
    }

    fun storePlayerMoves(@GameBoardPosition.Position index: Int) {
        getGameBoard()[index / 3][index % 3] = getCurrentPlayer()
    }

    fun getGameBoardByIndex(position: Int) = getGameBoard()[position / 3][position % 3]

    private fun getGameBoard(): Array<IntArray> {
        return mGameBoard
    }

}