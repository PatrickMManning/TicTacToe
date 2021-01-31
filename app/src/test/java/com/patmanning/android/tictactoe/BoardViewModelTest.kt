package com.patmanning.android.tictactoe

import org.junit.Test
import org.junit.Before
import org.junit.Assert.*

class BoardViewModelTest {
    private lateinit var boardViewModel: BoardViewModel

    @Before
    fun setup() {
        boardViewModel = BoardViewModel()
    }

    @Test
    fun getValue_isX() {
        boardViewModel.boardFromString("x, , ; , , ; , , ;")
        assertEquals("x", boardViewModel.getValue(0,0))
    }

    @Test
    fun updateTurn_flipsValues() {
        assertEquals("x", boardViewModel.currentTurn)
        boardViewModel.updateTurn()
        assertEquals("o", boardViewModel.currentTurn)
        boardViewModel.updateTurn()
        assertEquals("x", boardViewModel.currentTurn)
    }

    @Test
    fun getWinner_topRowXWins() {
        boardViewModel.boardFromString("x,x,x; , , ; , , ")
        assertEquals("x", boardViewModel.getWinner())
    }

    @Test
    fun getWinner_midRowXWins() {
        boardViewModel.boardFromString(" , , ;x,x,x; , , ")
        assertEquals("x", boardViewModel.getWinner())
    }

    @Test
    fun getWinner_bottomRowXWins() {
        boardViewModel.boardFromString(" , , ; , , ;x,x,x;")
        assertEquals("x", boardViewModel.getWinner())
    }

    @Test
    fun getWinner_leftColXWins() {
        boardViewModel.boardFromString("x, , ;x, , ;x, , ;")
        assertEquals("x", boardViewModel.getWinner())
    }

    @Test
    fun getWinner_centerColXWins() {
        boardViewModel.boardFromString(" ,x, ; ,x, ; ,x, ;")
        assertEquals("x", boardViewModel.getWinner())
    }

    @Test
    fun getWinner_rightColXWins() {
        boardViewModel.boardFromString(" , ,x; , ,x; , ,x;")
        assertEquals("x", boardViewModel.getWinner())
    }

    @Test
    fun getWinner_diagonalLeftRightXWins() {
        boardViewModel.boardFromString("x, , ; ,x, ; , ,x;")
        assertEquals("x", boardViewModel.getWinner())
    }

    @Test
    fun getWinner_diagonalRightLeftXWins() {
        boardViewModel.boardFromString(" , ,x; ,x, ;x, , ;")
        assertEquals("x", boardViewModel.getWinner())
    }

    @Test
    fun boardToString_matches() {
        boardViewModel.boardFromString(" , ,x; ,x, ;x, , ;")
        assertEquals(" , ,x; ,x, ;x, , ;", boardViewModel.boardToString())
    }

    @Test
    fun newGame_resetsValues() {
        boardViewModel.newGame()
        assertEquals(false, boardViewModel.hasWinner)
        assertEquals("x", boardViewModel.currentTurn)
        assertEquals(boardViewModel.defaultBoardString, boardViewModel.boardToString())
    }
}