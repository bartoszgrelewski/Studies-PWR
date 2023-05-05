package com.example.saper

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game_won.*

class gameWon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_won)
        val intent = intent
        val result = intent.getStringExtra("result")
        if (result == "Lose") {
            trophy.setImageResource(R.drawable.game_over)
            congrats.text = getString(R.string.lost_message)
            game_message.text = getString(R.string.game_loss)

        } else if (result == "Win") {
            trophy.setImageResource(R.drawable.trophy)
            congrats.text = getString(R.string.congratulations)
            game_message.text = getString(R.string.win_message)

        }
        continue_play?.setOnClickListener {
            val intents = Intent(this@gameWon, MainActivity::class.java)
            startActivity(intents)
        }
    }
}