package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        var gotoprof=findViewById<ImageView>(R.id.backtoprof)
        gotoprof.setOnClickListener{
            startActivity(Intent(this,ProfileHome::class.java))
        }
    }
}