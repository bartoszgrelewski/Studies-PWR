package com.example.lab01_zad2_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var playerScore: TextView
    private lateinit var phoneScore: TextView
    private lateinit var rockImage: ImageButton
    private lateinit var paperImage: ImageButton
    private lateinit var scissorsImage: ImageButton
    private lateinit var resetButton: Button

    private var phoneMove = 0
    private var playerMove = 0
    private var playerResult = 0
    private var phoneResult = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerScore = findViewById(R.id.playerScore)
        phoneScore = findViewById(R.id.phoneScore)
        rockImage = findViewById(R.id.rock)
        paperImage = findViewById(R.id.paper)
        scissorsImage = findViewById(R.id.scissors)
        resetButton = findViewById(R.id.resetButton)

        paperImage.setOnClickListener(this)
        rockImage.setOnClickListener(this)
        scissorsImage.setOnClickListener(this)
        resetButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        generateRandomPhoneMove()

        playerMove = when(v.id) {
            R.id.rock -> 1
            R.id.paper -> 2
            R.id.scissors -> 3
            R.id.resetButton -> 0
            else -> -1
        }
        if (playerMove == 0) {
            resetGame()
            Toast.makeText(this, "Reset game", Toast.LENGTH_SHORT).show()
        } else {
            decideWhoWonRound()
        }
    }

    private fun decideWhoWonRound() {

        if(playerMove == phoneMove){
            Toast.makeText(this, "Draw, nobody won", Toast.LENGTH_SHORT).show()
        }
        if (playerMove == 1 && phoneMove == 3 ||
            playerMove == 2 && phoneMove == 1 ||
            playerMove == 3 && phoneMove == 2) {

            Toast.makeText(this, "Player has won", Toast.LENGTH_SHORT).show()
            playerResult++
            playerScore.text = "Player: " + playerResult
            } else {
                Toast.makeText(this, "Phone has won", Toast.LENGTH_SHORT).show()
                phoneResult++
                phoneScore.text = "Phone: " + phoneResult
            }
        }

    private fun resetGame() {
        playerResult = 0
        phoneResult = 0
        playerScore.text = "Player: " + playerResult
        phoneScore.text = "Phone: " + phoneResult
    }

    private fun generateRandomPhoneMove() {
        phoneMove = Random.nextInt(3) + 1
    }
}