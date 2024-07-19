package com.example.internshipproj1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var register=findViewById<Button>(R.id.Reg)
        var email=findViewById<EditText>(R.id.Email)
        var password=findViewById<EditText>(R.id.Password)
        register.setOnClickListener{
            startActivity(Intent(this, Login::class.java))
//            var mAuth = FirebaseAuth.getInstance()
//            mAuth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnSuccessListener {
//                Toast.makeText(this, "Done", Toast.LENGTH_LONG).show()
//            }
//                .addOnFailureListener{
//                    Toast.makeText(this, "Not Done", Toast.LENGTH_LONG).show()
//
//                }
        }
    }
}
