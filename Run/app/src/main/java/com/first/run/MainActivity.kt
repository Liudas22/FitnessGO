package com.first.run

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.EditText
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.button)
        loginButton.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val registerButton = findViewById<Button>(R.id.button2)
        registerButton.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

    }
}