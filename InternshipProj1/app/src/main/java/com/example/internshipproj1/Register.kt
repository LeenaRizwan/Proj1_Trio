package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

//So now we'll do Email Verification Here
//The idea is the same as The other, except ill do it manually, and we wont make the account here
//we'll pass on the details as Intent
//Use Intent to send a randomised code between 1000-9999
//If typed==generated code
//then make account and head to Home
//else show error message, rec user to send themself another code
class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var register=findViewById<Button>(R.id.Reg)
        var login=findViewById<Button>(R.id.Log)
        var email=findViewById<EditText>(R.id.Email)
        var password=findViewById<EditText>(R.id.Password)
        register.setOnClickListener{
            var mAuth = FirebaseAuth.getInstance()
            mAuth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString()).addOnSuccessListener {
                startActivity(Intent(this,EmailVerif::class.java))
            }
                .addOnFailureListener{
                    Toast.makeText(this, "Not Done", Toast.LENGTH_LONG).show()

                }
        }
        login.setOnClickListener{
            startActivity(Intent(this,Login::class.java))
        }
    }
}
