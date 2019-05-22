package com.katatictactoe

import org.junit.Assert.*
import org.junit.Test


class TicTacToeViewModelUnitTest {

    private var ticTacToeViewModel = TicTacToeViewModel()

    @Test
    fun testShouldCheckWhetherTheFirstMoveAvailableForPlayerX() {
        assertEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_X_ID)
    }

    @Test
    fun testToStoreThePlayerMoveAndShouldCheckPlayerAtThatParticularIndexIsSameWhoStored() {
        assertNotNull(ticTacToeViewModel.storePlayerMoves(GameBoardPosition.INDEX_TOP_MIDDLE))
        assertSame(ticTacToeViewModel.getGameBoardByIndex(GameBoardPosition.INDEX_TOP_MIDDLE), Player.PLAYER_X_ID)
    }

}
