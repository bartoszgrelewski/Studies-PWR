package com.example.saper


import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_minesweeper_game.*
import kotlin.random.Random

class MinesweeperGame : AppCompatActivity() {

    private var flaggedmines = 0

    private var isFirstmove = true
    private var status = Status.ONGOINING
    private var rows = 0
    private var columns = 0
    private var mines = 0
    private var mineboard = Array(rows) { Array(columns) { MineCell(this) } }
    private var playStarted = false

    companion object {
        const val MINE = -1
        const val reveal = "reveal"
        const val flag = "flag"
        val movement = arrayOf(-1, 0, 1)
    }

    private var toMove: String = reveal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minesweeper_game)
        //starts the timer
        if (!playStarted) {
            timer.base = SystemClock.elapsedRealtime()
            timer.start()
            Toast.makeText(this, "Powodzenia!", Toast.LENGTH_SHORT).show()
            playStarted
        }
        //mines left will show the number of mines not flagged
        minesLeftinfo.text = mines.toString()
        //create board function will create the layout as per dimensions
        rows = 9
        columns = 9
        mines = 10
        createBoard()

        restart.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            with(builder) {
                setTitle("Zrestartuj gre")
                setMessage("Gra w toku, na pewno chcesz reset?")
                setPositiveButton("Tak") { _, _ ->
                    val intents = intent
                    finish()
                    startActivity(intents)
                }
                setNegativeButton("Nie") { _, _ -> }
            }
            val alert = builder.create()
            alert.show()
        }
    }

    private fun createBoard() {
        mineboard = Array(rows) {
            Array(columns) {
            MineCell(this)
            }
        }
        val params1 = LinearLayout.LayoutParams(
            0, LinearLayout.LayoutParams.MATCH_PARENT
        )

        val params2 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 0
        )

        var id = 1
        choice.setOnClickListener {
            if (toMove == reveal) {
                toMove = flag
                choice.setImageResource(R.drawable.flag)
            } else {
                toMove = reveal
                choice.setImageResource(R.drawable.bomb)
            }
        }
        for (i in 0 until rows) {
            val linearLayout = LinearLayout(this)
            linearLayout.orientation = LinearLayout.HORIZONTAL
            linearLayout.layoutParams = params2
            params2.weight = 1.0F

            //  params2.bottomMargin= 5
            for (j in 0 until columns) {
                val button = MineCell(this)
                mineboard[i][j] = button
                button.id = id

                id++
                button.layoutParams = params1
                params1.weight = 1.0F
                button.setBackgroundResource(R.drawable.notopenednew)
                // params1.rightMargin=5
                linearLayout.addView(button)
                button.setOnClickListener {
                    // if the user ha clicked the cell first time , the mines will be setup in the game ensuring the first clicked cell isnt a mine/bomb.
                    if (isFirstmove) {
                        isFirstmove = false
                        setupmines(i, j)
                    }
                    move(toMove, i, j)
                    displayBoard()
                    if (isComplete() && status != Status.LOST) {
                        status = Status.WON
                        finalResult()
                    }
                }
            }
            board.addView(linearLayout)
        }
    }

    private fun displayBoard() {
        mineboard.forEach { row ->
            row.forEach {
                if (it.isRevealed)
                    setImage(it)
                else if (it.isFlagged) {
                    if (status == Status.LOST && !it.isMine)
                        it.setBackgroundResource(R.drawable.crossedflag)
                    else
                        it.setBackgroundResource(R.drawable.flag)
                } else if (status == Status.LOST && it.value == MINE)
                    it.setBackgroundResource(R.drawable.mine)
                else if (status == Status.WON && it.value == MINE)
                    it.setBackgroundResource(R.drawable.flag)
                else
                    it.text = " "
            }
        }
    }

    private fun setImage(minecell: MineCell) {
        with(minecell) {
            when (value) {
                0 -> setBackgroundResource(R.drawable.zero)
                1 -> setBackgroundResource(R.drawable.one)
                2 -> setBackgroundResource(R.drawable.two)
                3 -> setBackgroundResource(R.drawable.three)
                4 -> setBackgroundResource(R.drawable.four)
                5 -> setBackgroundResource(R.drawable.five)
                6 -> setBackgroundResource(R.drawable.six)
                7 -> setBackgroundResource(R.drawable.seven)
                8 -> setBackgroundResource(R.drawable.eight)
            }
        }
    }

    // reveals or flags the cell as per option chosen by user
    private fun move(choice: String, x: Int, y: Int): Boolean {

        when (choice) {
            reveal -> {
                if (mineboard[x][y].isFlagged || mineboard[x][y].isRevealed)
                    return false

                if (mineboard[x][y].value == MINE) {
                    status = Status.LOST
                    finalResult()
                    return true
                } else
                    reveal(x, y)
                return true
            }

            flag -> {
                with(mineboard[x][y])
                {
                    if (isRevealed)
                        return false
                    else if (isFlagged) {
                        isFlagged = false
                        setBackgroundResource(R.drawable.notopenednew)
                        flaggedmines--
                        minesLeftinfo.text = (mines - flaggedmines).toString()
                        return true

                    } else {
                        if (flaggedmines == mines) {
                            Toast.makeText(
                                this@MinesweeperGame,
                                "You cannot flag more mines",
                                Toast.LENGTH_SHORT
                            ).show()
                            return false
                        } else {
                            isFlagged = true
                            flaggedmines++
                            setBackgroundResource(R.drawable.flag)
                            minesLeftinfo.text = (mines - flaggedmines).toString()
                            return true
                        }
                    }
                }
            }
        }
        return true
    }

    private fun finalResult() {
        //timer stops as soon as game finishes
        timer.stop()

        if (status == Status.WON) {
            val intent = Intent(this, gameWon::class.java).apply {
                putExtra("result", "Won")
            }
            startActivity(intent)
        } else if (status == Status.LOST) {
            val intent = Intent(this, gameWon::class.java).apply {
                putExtra("result", "Lose")
            }
            startActivity(intent)
        }
    }

    // isComplete() function checks for the win conditions
    private fun isComplete(): Boolean {
        var minesMarked = true
        var valuesRevealed = true
        mineboard.forEach { row ->
            row.forEach {
                if (it.value == MINE) {
                    if (!it.isFlagged)
                        minesMarked = false
                } else if (it.value != MINE) {
                    if (!it.isRevealed)
                        valuesRevealed = false
                }
            }
        }
        if (minesMarked && valuesRevealed) {
            status = Status.WON
            return true
        }
        return minesMarked && valuesRevealed
    }

    private fun reveal(x: Int, y: Int) {

        if (!mineboard[x][y].isRevealed && !mineboard[x][y].isFlagged && mineboard[x][y].value != -1) {
            mineboard[x][y].isRevealed = true
            if (mineboard[x][y].value == 0) {
                for (i in movement)
                    for (j in movement)
                        if ((i != 0 || j != 0) && ((x + i) in 0 until rows) && ((y + j) in 0 until columns))
                            reveal(x + i, y + j)
            }
        }
    }

    // if the user clicks back button, an alert dialog will be shown confirming the action
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle(getString(R.string.exit_game))
            setMessage(getString(R.string.abort))
            setPositiveButton(
                "Tak"
            ) { dialog, which ->
                finalResult()
                super.onBackPressed()
            }
            setNegativeButton(
                "Nie"
            ) { dialog, which ->
            }
        }
        val alert = builder.create()
        alert.show()
    }

    //mines are set randomly
    private fun setupmines(i: Int, j: Int) {

        var m = 1
        while (m <= mines) {
            val x = Random(System.nanoTime()).nextInt(0, rows)
            val y = Random(System.nanoTime()).nextInt(0, columns)
            if (((x in i - 1..i + 1) && (y in j - 1..j + 1)) || mineboard[x][y].isMine) {
                continue
            } else
                mineboard[x][y].value = -1
            mineboard[x][y].isMine = true
            updateNeighbours(x, y)
            m++
        }
    }

    private fun updateNeighbours(x: Int, y: Int) {
        for (i: Int in movement) {
            for (j in movement) {
                if (((x + i) in 0 until rows) && ((y + j) in 0 until columns) && mineboard[x + i][y + j].value != -1) {
                    mineboard[x + i][y + j].value++
                }
            }
        }
    }
}

enum class Status {
    WON,
    ONGOINING,
    LOST
}