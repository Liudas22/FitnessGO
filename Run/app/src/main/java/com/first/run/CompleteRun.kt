package com.first.run

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar

class CompleteRun : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_run)

        val saveButton = findViewById<Button>(R.id.btSave3)
        saveButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val deleteButton = findViewById<Button>(R.id.btDelete3)
        deleteButton.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        val volumeSeekBar: SeekBar = findViewById(R.id.seekBar)
        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //cia galima perduoti šita skaičių išsaugojimui
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
    })

        val saveButton3 = findViewById<Button>(R.id.btSave3)
        saveButton3.setOnClickListener{
            val intent = Intent(this, all_runsframe::class.java)
            startActivity(intent)
        }

        val deleteButton3 = findViewById<Button>(R.id.btDelete3)
        deleteButton3.setOnClickListener{
            val intent = Intent(this, Start_Run::class.java)
            startActivity(intent)
        }
    }
}

