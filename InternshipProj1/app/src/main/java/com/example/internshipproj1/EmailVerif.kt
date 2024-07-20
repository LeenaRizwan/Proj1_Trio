package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class EmailVerif : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_verif)
        var sendemail=findViewById<Button>(R.id.sendagain)
        var Login=findViewById<Button>(R.id.log)
        var mAuth=FirebaseAuth.getInstance()
        sendemail.setOnClickListener{
            mAuth.currentUser?.sendEmailVerification()
        }
        Login.setOnClickListener{
                startActivity(Intent(this,Login::class.java))
        }
    }
}