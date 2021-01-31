package com.patmanning.android.tictactoe

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "BoardViewModel"

class BoardViewModel : ViewModel() {
    val defaultBoardString = " , , ; , , ; , , ;"
    val defaultTurn = "x"

    private var board: List<ArrayList<String>> =
        listOf(
            arrayListOf(" ", " ", " "),
            arrayListOf(" ", " ", " "),
            arrayListOf(" ", " ", " ")
        )

    var currentTurn = "x"

    var hasWinner = false

    fun getValue(row: Int, col: Int): String {
        return board[row][col]
    }

    fun setValueToCurrentTurn(row: Int, col: Int) {
        board[row][col] = currentTurn
    }

    fun updateTurn() {
        currentTurn = if (currentTurn == "x") "o" else "x"
    }

    fun getWinner(): String {
        if (board[0][0] != " " && board[0][0] == board[0][1] && board[0][0] == board[0][2]) {
            hasWinner = true
            return board[0][0]
        }
        if (board[1][0] != " " && board[1][0] == board[1][1] && board[1][0] == board[1][2]) {
            hasWinner = true
            return board[1][0]
        }
        if (board[2][0] != " " && board[2][0] == board[2][1] && board[2][0] == board[2][2]) {
            hasWinner = true
            return board[2][0]
        }
        if (board[0][0] != " " && board[0][0] == board[1][0] && board[0][0] == board[2][0]) {
            hasWinner = true
            return board[0][0]
        }
        if (board[0][1] != " " && board[0][1] == board[1][1] && board[0][1] == board[2][1]) {
            hasWinner = true
            return board[0][1]
        }
        if (board[0][2] != " " && board[0][2] == board[1][2] && board[0][2] == board[2][2]) {
            hasWinner = true
            return board[0][2]
        }
        if (board[0][0] != " " && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            hasWinner = true
            return board[0][0]
        }
        if (board[0][2] != " " && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            hasWinner = true
            return board[0][2]
        }
        return ""
    }

    fun boardToString(): String {
        return board.joinToString(";") { row -> row.joinToString(",") }
    }

    fun boardFromString(turns: String) {
        board = turns.split(";").map { row -> ArrayList(row.split(",")) }
    }

    fun newGame() {
        hasWinner = false
        currentTurn = defaultTurn
        boardFromString(defaultBoardString)
    }
}