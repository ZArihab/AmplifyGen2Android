package com.example.amplifygen2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.amplifyframework.core.Amplify

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        super.onStart()
        val textViewSignup = findViewById<TextView>(R.id.textViewSignup)
        textViewSignup.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            var editTextEmail = findViewById<EditText>(R.id.editTextEmail)
            var email = editTextEmail.text.toString()
            var editTextPassword = findViewById<EditText>(R.id.editTextPassword)
            var password = editTextPassword.text.toString()
            Amplify.Auth.signIn(email, password,
                { result ->
                    if (result.isSignedIn) {
                        Log.i("AuthQuickstart", "Sign in succeeded")
                        val intent =Intent(this,DashboardActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.i("AuthQuickstart", "Sign in not complete")
                    }
                },
                { Log.e("AuthQuickstart", "Failed to sign in", it) }
            )
        }
    }

}