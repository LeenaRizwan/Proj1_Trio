package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ResetPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        var mAuth=FirebaseAuth.getInstance()
        var tologin=findViewById<Button>(R.id.log)
        var resend=findViewById<Button>(R.id.sendagain)
        var email=findViewById<EditText>(R.id.Email)
        tologin.setOnClickListener{
            startActivity(Intent(this,Login::class.java))
        }
        resend.setOnClickListener{
            mAuth.sendPasswordResetEmail(email.text.toString()).addOnSuccessListener {
                Toast.makeText(this, "Email sent",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "Email not sent",Toast.LENGTH_SHORT).show()
            }
        }
    }
}