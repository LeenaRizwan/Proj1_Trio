package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InfoPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_page)

        var rebutton=findViewById<Button>(R.id.RegButton)
        rebutton.setOnClickListener{
            startActivity(Intent(this,Register::class.java))
        }
    }
}