package com.patmanning.android.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import org.w3c.dom.Text

private const val TAG = "MainActivity"
private const val KEY_TURN = "turn"
private const val KEY_BOARD = "board"

class MainActivity : AppCompatActivity() {

    private lateinit var topLeft: TextView
    private lateinit var topCenter: TextView
    private lateinit var topRight: TextView
    private lateinit var midLeft: TextView
    private lateinit var midCenter: TextView
    private lateinit var midRight: TextView
    private lateinit var bottomLeft: TextView
    private lateinit var bottomCenter: TextView
    private lateinit var bottomRight: TextView

    private lateinit var currentTurnDisplay: TextView
    private lateinit var newGame: Button

    private val boardViewModel: BoardViewModel by lazy {
        ViewModelProviders.of(this).get(BoardViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentTurn = savedInstanceState?.getString(KEY_TURN, boardViewModel.defaultTurn)
            ?: boardViewModel.defaultTurn
        boardViewModel.currentTurn = currentTurn

        val currentBoard =
            savedInstanceState?.getString(KEY_BOARD, boardViewModel.defaultBoardString)
                ?: boardViewModel.defaultBoardString
        boardViewModel.boardFromString(currentBoard)

        topLeft = findViewById(R.id.top_left_square)
        topCenter = findViewById(R.id.top_center_square)
        topRight = findViewById(R.id.top_right_square)
        midLeft = findViewById(R.id.mid_left_square)
        midCenter = findViewById(R.id.mid_center_square)
        midRight = findViewById(R.id.mid_right_square)
        bottomLeft = findViewById(R.id.bottom_left_square)
        bottomCenter = findViewById(R.id.bottom_center_square)
        bottomRight = findViewById(R.id.bottom_right_square)
        currentTurnDisplay = findViewById(R.id.current_turn_value)
        newGame = findViewById(R.id.new_game)

        topLeft.setOnClickListener {
            if (clicked(0, 0)) {
                topLeft.setText(boardViewModel.currentTurn)
                boardViewModel.updateTurn()
                currentTurnDisplay.setText(boardViewModel.currentTurn)
            }
            checkWinner()
        }
        topCenter.setOnClickListener {
            if (clicked(0, 1)) {
                topCenter.setText(boardViewModel.currentTurn)
                boardViewModel.updateTurn()
                currentTurnDisplay.setText(boardViewModel.currentTurn)
            }
            checkWinner()
        }
        topRight.setOnClickListener {
            if (clicked(0, 2)) {
                topRight.setText(boardViewModel.currentTurn)
                boardViewModel.updateTurn()
                currentTurnDisplay.setText(boardViewModel.currentTurn)
            }
            checkWinner()
        }
        midLeft.setOnClickListener {
            if (clicked(1, 0)) {
                midLeft.setText(boardViewModel.currentTurn)
                boardViewModel.updateTurn()
                currentTurnDisplay.setText(boardViewModel.currentTurn)
            }
            checkWinner()
        }
        midCenter.setOnClickListener {
            if (clicked(1, 1)) {
                midCenter.setText(boardViewModel.currentTurn)
                boardViewModel.updateTurn()
                currentTurnDisplay.setText(boardViewModel.currentTurn)
            }
            checkWinner()
        }
        midRight.setOnClickListener {
            if (clicked(1, 2)) {
                midRight.setText(boardViewModel.currentTurn)
                boardViewModel.updateTurn()
                currentTurnDisplay.setText(boardViewModel.currentTurn)
            }
            checkWinner()
        }
        bottomLeft.setOnClickListener {
            if (clicked(2, 0)) {
                bottomLeft.setText(boardViewModel.currentTurn)
                boardViewModel.updateTurn()
                currentTurnDisplay.setText(boardViewModel.currentTurn)
            }
            checkWinner()
        }
        bottomCenter.setOnClickListener {
            if (clicked(2, 1)) {
                bottomCenter.setText(boardViewModel.currentTurn)
                boardViewModel.updateTurn()
                currentTurnDisplay.setText(boardViewModel.currentTurn)
            }
            checkWinner()
        }
        bottomRight.setOnClickListener {
            if (clicked(2, 2)) {
                bottomRight.setText(boardViewModel.currentTurn)
                boardViewModel.updateTurn()
                currentTurnDisplay.setText(boardViewModel.currentTurn)
            }
            checkWinner()
        }

        newGame.setOnClickListener {
            boardViewModel.newGame()
            refreshBoard()
        }
        refreshBoard()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_TURN, boardViewModel.currentTurn)
        outState.putString(KEY_BOARD, boardViewModel.boardToString())
    }

    private fun clicked(row: Int, col: Int): Boolean {
        if (!boardViewModel.hasWinner && boardViewModel.getValue(row, col) == " ") {
            boardViewModel.setValueToCurrentTurn(row, col)
            return true
        }
        return false
    }

    private fun checkWinner() {
        if (boardViewModel.getWinner() != "") {
            Toast.makeText(this, "Winner: " + boardViewModel.getWinner(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun refreshBoard() {
        topLeft.setText(boardViewModel.getValue(0, 0))
        topCenter.setText(boardViewModel.getValue(0, 1))
        topRight.setText(boardViewModel.getValue(0, 2))
        midLeft.setText(boardViewModel.getValue(1, 0))
        midCenter.setText(boardViewModel.getValue(1, 1))
        midRight.setText(boardViewModel.getValue(1, 2))
        bottomLeft.setText(boardViewModel.getValue(2, 0))
        bottomCenter.setText(boardViewModel.getValue(2, 1))
        bottomRight.setText(boardViewModel.getValue(2, 2))
        currentTurnDisplay.setText(boardViewModel.currentTurn)
    }
}