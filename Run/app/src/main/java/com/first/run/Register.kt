package com.first.run

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

private var spinner: Spinner? = null
private var arrayAdapter: ArrayAdapter<String>? = null
private var itemsList = arrayOf(
    "Select a country...",
    "Austria",
    "Italy",
    "Belgium",
    "Latvia",
    "Bulgaria",
    "Croatia",
    "Cyprus",
    "Czechia",
    "Denmark",
    "Estonia",
    "Finland",
    "France",
    "Germany",
    "Greece",
    "Hungary",
    "Ireland",
    "Lithuania",
    "Luxembourg",
    "Malta",
    "Netherlands",
    "Poland",
    "Portugal",
    "Romania",
    "Slovakia",
    "Slovenia",
    "Spain",
    "Sweden")


class Register : AppCompatActivity(), AdapterView.OnItemSelectedListener
{
    private lateinit var mAuth : FirebaseAuth
    //private lateinit var refUsers : DataBaseReference
    private var firebaseUserID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        spinner = findViewById(R.id.spinner)
        arrayAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, itemsList)
        spinner?.adapter = arrayAdapter
        spinner?.onItemSelectedListener = this

        val resetButton = findViewById<Button>(R.id.reset)

        val submitButton = findViewById<Button>(R.id.submit)

        val backButton = findViewById<Button>(R.id.back)

        val username_ed = findViewById<EditText>(R.id.et_user_name)
        val password_ed = findViewById<EditText>(R.id.et_password)
        val confirm = findViewById<EditText>(R.id.confirm_password)
        val email_ed = findViewById<EditText>(R.id.et_Email)
        val age_ed = findViewById<EditText>(R.id.et_age)
        val country_ed = findViewById<Spinner>(R.id.spinner)
        mAuth = FirebaseAuth.getInstance()

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        resetButton.setOnClickListener{
            username_ed.setText("")
            password_ed.setText("")
            confirm.setText("")
            email_ed.setText("")
            age_ed.setText("")
            country_ed.setSelection(0)
        }

        submitButton.setOnClickListener{
            registerUser()
        }
    }

    private fun registerUser() {
        val username: String = findViewById<EditText>(R.id.et_user_name).text.toString()
        val password: String = findViewById<EditText>(R.id.et_password).text.toString()
        val confirm_password: String = findViewById<EditText>(R.id.confirm_password).text.toString()
        val email: String = findViewById<EditText>(R.id.et_Email).text.toString()
        val age: String = findViewById<EditText>(R.id.et_age).text.toString()
        val country: String = findViewById<Spinner>(R.id.spinner).selectedItem.toString()

        if(username == ""){
            Toast.makeText(this, "Please enter username...", Toast.LENGTH_LONG).show()
        }
        else if(password == ""){
            Toast.makeText(this, "Please enter password...", Toast.LENGTH_LONG).show()
        }
        else if(confirm_password == ""){
            Toast.makeText(this, "Please confirm password...", Toast.LENGTH_LONG).show()
        }
        else if(email == ""){
            Toast.makeText(this, "Please enter email...", Toast.LENGTH_LONG).show()
        }
        else if(age == ""){
            Toast.makeText(this, "Please enter your age...", Toast.LENGTH_LONG).show()
        }
        else if(country == "Select a country..."){
            Toast.makeText(this, "Please select country...", Toast.LENGTH_LONG).show()
        }
        else if(password != confirm_password){
            Toast.makeText(this, "Passwords are not matching!", Toast.LENGTH_LONG).show()
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                if(task.isSuccessful)
                {
                    firebaseUserID = mAuth.currentUser!!.uid
                    //refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)
                    var refUser = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)
                    val userHashMap = HashMap<String, Any>()
                    userHashMap["uid"] = firebaseUserID
                    userHashMap["username"] = username
                    userHashMap["profile"] = "32f85f84-4491-4f30-a50e-82e68debadb8"
                    userHashMap["cover"] = "9bc307c2-9ed3-4597-81f4-25c5a8524802"
                    userHashMap["search"] = username.toLowerCase()

                    refUser.updateChildren(userHashMap)
                        .addOnCompleteListener { task1 ->
                            if(task1.isSuccessful){
                                val intent = Intent(this, Login::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                finish()
                            }
                        }
                }
                else
                {
                    Toast.makeText(this, "Error Message: " + task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(applicationContext, "Nothing selected", Toast.LENGTH_LONG).show()
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var items:String = parent?.getItemAtPosition(position) as String
        Toast.makeText(applicationContext, "$items", Toast.LENGTH_LONG).show()
    }
}
