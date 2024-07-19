package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var login=findViewById<Button>(R.id.log)
        var reset=findViewById<Button>(R.id.Reset)
        var register=findViewById<Button>(R.id.Reg)
        var email=findViewById<EditText>(R.id.Email)
        var pass=findViewById<EditText>(R.id.Password)
        var mAuth=FirebaseAuth.getInstance()
        register.setOnClickListener{
            startActivity(Intent(this,Register::class.java))
        }
        login.setOnClickListener{
                mAuth.signInWithEmailAndPassword(
                    email.text.toString(),
                    pass.text.toString()
                ).addOnSuccessListener {
                    Toast.makeText(this,"Succeeded To SignIn", Toast.LENGTH_LONG).show()
                    finish()
                }
                    .addOnFailureListener {

                        Log.e("Signin_Error",it.message.toString())
                        Toast.makeText(this,"Failed To SignIn", Toast.LENGTH_LONG).show()
                    }
        }
        reset.setOnClickListener{
            startActivity(Intent(this,ResetPassword::class.java))
        }

    }
}