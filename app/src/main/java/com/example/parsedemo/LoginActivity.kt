package com.example.parsedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (ParseUser.getCurrentUser() != null) {
            goToMainActivity()
        }

        // Assign vars to views
        val loginButton = findViewById<Button>(R.id.login_button)
        val usernameEt = findViewById<EditText>(R.id.username_et)
        val passwordEt = findViewById<EditText>(R.id.password_et)

        val registerButton = findViewById<Button>(R.id.register_button)

        loginButton.setOnClickListener {
            val username = usernameEt.text.toString()
            val password = passwordEt.text.toString()
            loginUser(username, password)
        }

        registerButton.setOnClickListener {
            val username = usernameEt.text.toString()
            val password = passwordEt.text.toString()
            signUpUser(username, password)
        }
    }

    // Send credentials to parse to attempt authentication
    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG, "Successfully logged in user")
                goToMainActivity()
            } else {
                e.printStackTrace()
                Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show()
            }
        }))
    }
 // login, setting, list, detail, maps, intent

    // Navigate to Main activity
    private fun goToMainActivity(){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish() // So that we can't come back to the log in page by hitting back
    }


    // Get user information and send to parse
    private fun signUpUser(username: String, password: String) {

        val user = ParseUser()

        user.username = username
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // User successfully created new account
                Log.i(TAG, "User successfully signed up")
                goToMainActivity()
            } else {
                Toast.makeText(this, "Error signing up", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    companion object {
        val TAG = "LoginActivity"
    }
}