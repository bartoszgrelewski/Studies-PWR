package com.example.hangman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.arrayOf

class MainActivity : AppCompatActivity() {
    var pass = ""
    var showedPass = ""
    var counter = 0
    var toWin = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val keyboard = findViewById<RecyclerView>(R.id.recyclerView)
        keyboard.layoutManager = GridLayoutManager(this, 10)
        keyboard.adapter = CustomAdapter(generateKeyboard())

        pass = getRandom()
        generatePass()
        findViewById<TextView>(R.id.textView).text = showedPass

        val image : ImageView = findViewById(R.id.imageView)
        image.setImageResource(R.drawable.wisielec0)

    }

    fun reset(view: View) {
        pass = ""
        showedPass = ""
        counter = 0
        toWin = 0

        val keyboard = findViewById<RecyclerView>(R.id.recyclerView)
        keyboard.layoutManager = GridLayoutManager(this, 10)
        keyboard.adapter = CustomAdapter(generateKeyboard())

        pass = getRandom()
        generatePass()
        findViewById<TextView>(R.id.textView).text = showedPass

        val image : ImageView = findViewById(R.id.imageView)
        image.setImageResource(R.drawable.wisielec0)
    }

    fun click(view: View) {
        val button = view as Button
        button.setBackgroundColor(1)
        button.isClickable = false

        var correct = false
        var temp = ""

        if(counter < 12 && !(toWin == pass.length)) {
            for(i in 0..pass.length - 1) {

                var currentPass = ""
                currentPass += pass.get(i).toUpperCase()
                if(currentPass.equals(button.text)) {
                    correct = true
                    toWin++
                    temp += currentPass + " "
                } else {
                    temp += showedPass.get(i * 2) + " "
                }
            }

            if(correct) {
                showedPass = temp
                findViewById<TextView>(R.id.textView).text = showedPass
            } else {
                nextImage()
            }
        }
        if(toWin == pass.length) {
            Toast.makeText(this, "you won", Toast.LENGTH_SHORT).show()
        } else if(counter == 12) {
            Toast.makeText(this, "you lose", Toast.LENGTH_SHORT).show()
        }
    }

    fun generatePass() {
        for(i in 1..pass.length) showedPass += "_ "
    }

    fun nextImage() {
        val images = arrayOf(
            R.drawable.wisielec1,
            R.drawable.wisielec2,
            R.drawable.wisielec3,
            R.drawable.wisielec4,
            R.drawable.wisielec5,
            R.drawable.wisielec6,
            R.drawable.wisielec7,
            R.drawable.wisielec8,
            R.drawable.wisielec9,
            R.drawable.wisielec10,
            R.drawable.wisielec11,
            R.drawable.wisielec12
        )
        var image : ImageView = findViewById(R.id.imageView)
        image.setImageResource(images[counter])
        counter++
    }

    fun getRandom(): String {
        val array = resources.getStringArray(R.array.words)
        return array.get((0..array.size).random())
    }

    fun generateKeyboard() : List<String> {
        val data : MutableList<String> = ArrayList()
        data.add("Q")
        data.add("W")
        data.add("E")
        data.add("R")
        data.add("T")
        data.add("Y")
        data.add("U")
        data.add("I")
        data.add("O")
        data.add("P")
        data.add("A")
        data.add("S")
        data.add("D")
        data.add("F")
        data.add("G")
        data.add("H")
        data.add("J")
        data.add("K")
        data.add("L")
        data.add("Z")
        data.add("X")
        data.add("C")
        data.add("V")
        data.add("B")
        data.add("N")
        data.add("M")

        return data
    }
}

