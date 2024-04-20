package com.example.amplifygen2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession
import com.amplifyframework.core.Amplify

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Amplify.Auth.fetchAuthSession(
            {
                val session = it as AWSCognitoAuthSession
                if (session.isSignedIn) {
                   sendToDashboardActivity()
                }
                else {
                    sendToLoginActivity()

                }
            },
            { Log.e("AuthQuickStart", "Failed to fetch session", it) }
        )
    }

    private fun sendToLoginActivity() {
        var intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

    private fun sendToDashboardActivity() {
        var intent = Intent(this,DashboardActivity::class.java)
        startActivity(intent)
    }
}