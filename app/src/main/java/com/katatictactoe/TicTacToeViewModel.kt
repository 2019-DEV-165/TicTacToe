package com.katatictactoe

import androidx.lifecycle.ViewModel

class TicTacToeViewModel : ViewModel() {

    private var mCurrentPlayer = Player.PLAYER_X_ID

    fun getCurrentPlayer(): Int {
        return mCurrentPlayer
    }

}