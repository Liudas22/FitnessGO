package com.first.run

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    //private lateinit var refUsers : DataBaseReference
    private var firebaseUserID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.login)
        val resetButton = findViewById<Button>(R.id.btn_reset)
        val backButton = findViewById<Button>(R.id.back)

        mAuth = FirebaseAuth.getInstance()

       loginButton.setOnClickListener {
            loginUser()
       }
        resetButton.setOnClickListener{
            val email = findViewById<EditText>(R.id.email)
            val password = findViewById<EditText>(R.id.et_password)
            email.setText("")
            password.setText("")
        }
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser() {
        val email : String = findViewById<EditText>(R.id.email).text.toString()
        val password : String = findViewById<EditText>(R.id.et_password).text.toString()

        if(email == ""){
            Toast.makeText(this, "Please enter email...", Toast.LENGTH_LONG).show()
        }
        else if(password == ""){
            Toast.makeText(this, "Please enter password...", Toast.LENGTH_LONG).show()
        }
        else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val intent = Intent(this, Tutorial_Frame::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}