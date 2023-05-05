package com.example.tictactoegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.remember
import androidx.compose.runtime.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(TicTacToeViewModel::class.java)
        setContent {
            TicTacToeGame(viewModel)
        }
    }

    interface TicTacToeObserver {
        fun onGameStateChanged(board: Board, currentPlayer: Char, gameOver: Boolean)
    }

    @Composable
    fun TicTacToeGame(viewModel: TicTacToeViewModel) {
//        val board = viewModel.board
//        val currentPlayer = viewModel.currentPlayer
//
        val board = viewModel.board
        val currentPlayer = viewModel.currentPlayer
        val gameOver = viewModel.gameOver

        val observer = remember { object : TicTacToeObserver {
            override fun onGameStateChanged(board: Board, currentPlayer: Char, gameOver: Boolean) {
                // Re-compose the TicTacToeGame composable when the game state changes
                // by calling setContent again with the updated view model.
                setContent {
                    TicTacToeGame(viewModel)
                }
            }
        } }

        // Register the observer when the composable is active
        viewModel.addObserver(observer)

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            for (i in 0 until board.size) {
                Row {
                    for (j in 0 until board.size) {
                        Cell(
                            board.cells[i][j],
                            onClick = { viewModel.makeMove(i, j) }
                        )
                    }
                }
            }
            if (viewModel.gameOver) {
                Text(
                    text = "Game Over",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(top = 16.dp)
                )
            } else {
                Text(
                    text = "Current Player: $currentPlayer",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }

    @Composable
    fun Cell(value: Char, onClick: () -> Unit) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .clickable(onClick = onClick)
                .size(64.dp)
                .background(Color.White)
                .border(1.dp, Color.Black)
        ) {
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}

// A class representing the Tic-Tac-Toe board
data class Board(val size: Int, val cells: Array<Array<Char>>)

// A ViewModel class for managing the game state and user interface data
class TicTacToeViewModel : ViewModel() {
    private val observers = mutableListOf<MainActivity.TicTacToeObserver>()

    // Add a new observer
    fun addObserver(observer: MainActivity.TicTacToeObserver) {
        observers.add(observer)
    }

    // Remove an observer
    fun removeObserver(observer: MainActivity.TicTacToeObserver) {
        observers.remove(observer)
    }

    // Notify all observers of a game state change
    private fun notifyObservers() {
        observers.forEach { observer ->
            observer.onGameStateChanged(board, currentPlayer, gameOver)
        }
    }


    var board = Board(3, Array(3) { Array(3) { ' ' } })
    var currentPlayer = 'X'
    var gameOver = false

    // Make a move on the board
    fun makeMove(row: Int, col: Int) {
        if (!gameOver && board.cells[row][col] == ' ') {
            board.cells[row][col] = currentPlayer

            // Check for a winner or draw
            if (checkGameOver(board)) {
                gameOver = true
            } else {
                currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
            }
            notifyObservers()
        }
    }

    // Check if the game is over (either a player has won or there is a draw)
    private fun checkGameOver(board: Board): Boolean {
        val size = board.size
        val cells = board.cells

        // Check rows
        for (i in 0 until size) {
            var win = true
            for (j in 1 until size) {
                if (cells[i][j] != cells[i][0]) {
                    win = false
                    break
                }
            }
            if (win && cells[i][0] != ' ') {
                return true
            }
        }

        // Check columns
        for (j in 0 until size) {
            var win = true
            for (i in 1 until size) {
                if (cells[i][j] != cells[0][j]) {
                    win = false
                    break
                }
            }
            if (win && cells[0][j] != ' ') {
                return true
            }
        }

        // Check diagonal from top-left to bottom-right
        var win = true
        for (i in 1 until size) {
            if (cells[i][i] != cells[0][0]) {
                win = false
                break
            }
        }
        if (win && cells[0][0] != ' ') {
            return true
        }

        // Check diagonal from top-right to bottom-left
        win = true
        for (i in 1 until size) {
            if (cells[i][size - 1 - i] != cells[0][size - 1]) {
                win = false
                break
            }
        }
        if (win && cells[0][size - 1] != ' ') {
            return true
        }

        // Check for a draw
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (cells[i][j] == ' ') {
                    return false
                }
            }
        }
        return true
    }
}



