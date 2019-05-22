package com.katatictactoe

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class TicTacToeViewModelUnitTest {

    private var ticTacToeViewModel = TicTacToeViewModel()

    @Before
    fun resetGameBoard() {
        ticTacToeViewModel.resetGameBoard()
    }


    @Test
    fun testShouldCheckWhetherTheFirstMoveAvailableForPlayerX() {
        assertEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_X_ID)
    }

    @Test
    fun testToStoreThePlayerMoveAndShouldCheckPlayerAtThatParticularIndexIsSameWhoStored() {
        assertNotNull(ticTacToeViewModel.storePlayerMoves(GameBoardPosition.INDEX_TOP_MIDDLE))
        assertSame(ticTacToeViewModel.getGameBoardByIndex(GameBoardPosition.INDEX_TOP_MIDDLE), Player.PLAYER_X_ID)
    }

    @Test
    fun testShouldCheckSwappingOfPlayerXtoPlayerOAfterMoveSuccessfullyStored() {
        assertEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_X_ID)
        ticTacToeViewModel.storePlayerMoves(GameBoardPosition.INDEX_TOP_MIDDLE)
        assertEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_O_ID)
        assertNotEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_X_ID)

    }


}
