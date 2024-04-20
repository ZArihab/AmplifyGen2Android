package com.example.amplifygen2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    override fun onStart() {
        super.onStart()
        val signupButton = findViewById<Button>(R.id.buttonSignUp)
        signupButton.setOnClickListener {
            var editTextEmail = findViewById<EditText>(R.id.editTextEmail)
            var email = editTextEmail.text.toString()
            var editTextPassword = findViewById<EditText>(R.id.editTextPassword)
            var password = editTextPassword.text.toString()
            val options = AuthSignUpOptions.builder()
                .build()
            Amplify.Auth.signUp(email, password, options,
                { Log.i("AuthQuickStart", "Sign up succeeded: $it") },
                { Log.e ("AuthQuickStart", "Sign up failed", it) }
            )
        }

        val verifyCodeButton = findViewById<Button>(R.id.buttonVerifyCode)
        verifyCodeButton.setOnClickListener {
            var editTextEmail = findViewById<EditText>(R.id.editTextEmail)
            var email = editTextEmail.text.toString()
            var editTextVerificationCode = findViewById<EditText>(R.id.editTextVerificationCode)
            var code = editTextVerificationCode.text.toString()
            Amplify.Auth.confirmSignUp(
                email, code,
                { result ->
                    if (result.isSignUpComplete) {
                        Log.i("AuthQuickstart", "Confirm signUp succeeded")
                        val intent = Intent(this,LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Log.i("AuthQuickstart","Confirm sign up not complete")
                    }
                },
                { Log.e("AuthQuickstart", "Failed to confirm sign up", it) }
            )
        }
    }
}