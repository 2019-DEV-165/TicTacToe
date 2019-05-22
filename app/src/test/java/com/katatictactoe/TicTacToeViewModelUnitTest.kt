package com.katatictactoe

import org.junit.Assert.assertEquals
import org.junit.Test


class TicTacToeViewModelUnitTest {

    private var ticTacToeViewModel = TicTacToeViewModel()

    @Test
    fun testShouldCheckWhetherTheFirstMoveAvailableForPlayerX() {
        assertEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_X_ID)
    }

}
