package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SubmitFeedback : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_feedback)
        var feedback=findViewById<EditText>(R.id.Feedback)
        var stars=findViewById<EditText>(R.id.Stars)
        var submitbutton=findViewById<Button>(R.id.Submitbutton)
        var gotoprof=findViewById<ImageView>(R.id.backtoset)
        gotoprof.setOnClickListener{
            startActivity(Intent(this,ProfileHome::class.java))
        }
        submitbutton.setOnClickListener{
            if(feedback.text.isEmpty() || stars.text.isEmpty()){
                var errorm=findViewById<TextView>(R.id.errorM)
                errorm.setText("Please fill every query before pressing Submit!")
            }
            else if((stars.text.toString().toInt()>5)||(stars.text.toString().toInt()<0)){
                var errorm=findViewById<TextView>(R.id.errorM)
                errorm.setText("Please fill the Stars query with any number from 0 to 5!")
            }
            else{
                var mAuth=FirebaseAuth.getInstance()
                var myRef=FirebaseDatabase.getInstance().getReference("Reveiws").child(mAuth.currentUser?.email.toString().split("@")[0])
                myRef.child("Reveiw").setValue(feedback.text.toString())
                myRef.child("Stars").setValue(stars.text.toString())
            }
        }
    }
}