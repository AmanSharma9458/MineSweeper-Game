package com.example.android.minesweeper

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    var level = "easy"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = this.getSharedPreferences("time", Context.MODE_PRIVATE)
        bestTime.text="Best Time:"+sharedPreferences.getString("Best","00:00")+"s"
        val ltime: String? =sharedPreferences.getString("Last","00:00")

        lastGameTime.text= "Last Game Time:"+ltime+"s"

        /* if user clicks on easy radio button */
        easy.setOnClickListener {
            level="easy"
        }

/* if user clicks on medium difficulty radio button */
        medium.setOnClickListener{
            level="medium"
        }

/* if user clicks on hard difficulty radio button */
        hard.setOnClickListener{
            level="hard"
        }
//show user rules of minesweeper
        rules.setOnClickListener{
            showInstructions()
        }


        /* showing the instructions to the user */
        fun showInstructions() {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)

            builder.setTitle("INSTRUCTIONS")
            builder.setMessage("The purpose of the game is to open all the cells of the board which do not contain a bomb. You lose if you set off a bomb cell.\n" +
                    "\n" +
                    "Every non-bomb cell you open will tell you the total number of bombs in the eight neighboring cells. Once you are sure that a cell contains a bomb, you can right-click to put a flag it on it as a reminder. Once you have flagged all the bombs around an open cell, you can quickly open the remaining non-bomb cells by shift-clicking on the cell.\n" +
                    "\n" +
                    "To start a new game (abandoning the current one), just click on the yellow face button.\n" +
                    "\n" +
                    "Happy mine hunting!")

            builder.setCancelable(false)

            builder.setPositiveButton("OK"
            ){ dialog, which ->

            }

            val alertDialog = builder.create()
            alertDialog.show()
        }

        makeACustomBoard.setOnClickListener{
            if(customBoardLayout.visibility== View.GONE)
            {
                customBoardLayout.visibility=View.VISIBLE
            }
            else{
                customBoardLayout.visibility=View.GONE
            }
        }
        start.setOnClickListener {
            try {
                if (radio_group.checkedRadioButtonId == -1 || customBoardLayout.visibility == View.VISIBLE) {
                    // no radio buttons are checked
                    //after clicking submit button, this will transfer or pass the value which the user has entered
                    var heigh = Integer.parseInt(height.editText?.text.toString())
                    var widt = Integer.parseInt(width.editText?.text.toString())
                    var mine = Integer.parseInt(mines.editText?.text.toString())

                    /* passing the values to the gameplay activity */
                    val intent = Intent(this, GamePlay::class.java).apply {
                        putExtra("height", heigh)  //put the value
                        putExtra("width", widt)
                        putExtra("mines", mine)
                    }
                    startActivity(intent)
                } else {
                    // one of the radio buttons is checked
                    if (level == "") {
                        Toast.makeText(this, "Choose Valid Option", Toast.LENGTH_SHORT).show()
                    } else {
                        val intent = Intent(this, GamePlay::class.java).apply {
                            putExtra("selectedLevel", level)
                            putExtra("flag", 1)
                        }
                        startActivity(intent)
                    }

                }
            }
            catch(ex:Exception)
            {
                Toast.makeText(this, "Either fill custom data or play easy!", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private  fun showInstructions() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        builder.setTitle("INSTRUCTIONS")
        builder.setMessage("Minesweeper is a single-player puzzle video game.\n" +
                "\n" +
                "The objective of the game is to clear a rectangular board containing hidden \"mines\" or bombs without detonating any of them,\n" +
                " with help from clues about the number of neighbouring mines in each field.\n" +
                "\n" +
                "Happy fun!")

        builder.setCancelable(false)

        builder.setPositiveButton("OK"
        ){ dialog, which ->

        }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}