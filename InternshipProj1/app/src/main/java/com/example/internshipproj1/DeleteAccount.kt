package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class DeleteAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account)
        var mAuth= FirebaseAuth.getInstance()
        var email1=findViewById<EditText>(R.id.email1)
        var submitbutton=findViewById<Button>(R.id.Submitbutton)
        var gotoprof=findViewById<ImageView>(R.id.backtoset)
        gotoprof.setOnClickListener{
            startActivity(Intent(this,ProfileHome::class.java))
        }
        submitbutton.setOnClickListener{
            if(email1.text.isEmpty()){
                var errorm=findViewById<TextView>(R.id.errorM)
                errorm.setText("Please fill the query to delete your account!")
            }
            else if (mAuth.currentUser?.email.toString()!=email1.text.toString()){
                var errorm=findViewById<TextView>(R.id.errorM)
                errorm.setText("This email is not registered to this account.")
            }
            else{
                mAuth.currentUser?.delete()
                startActivity(Intent(this, StartPage::class.java))
            }
        }
    }
}