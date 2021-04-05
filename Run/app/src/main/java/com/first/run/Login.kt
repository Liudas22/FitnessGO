package com.first.run

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val submitButton = findViewById<Button>(R.id.btn_submit)
        submitButton.setOnClickListener{
            val intent = Intent(this, Start_Run::class.java)
            startActivity(intent)
        }
    }
}