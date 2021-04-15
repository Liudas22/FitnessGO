package com.first.run

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.EditText
import android.content.Intent

class Start_Run : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start__run)

       val startButton = findViewById<Button>(R.id.Start_Button)
        startButton.setOnClickListener{
            val intent = Intent(this, RunFrame::class.java)
            startActivity(intent)
        }
    }

}