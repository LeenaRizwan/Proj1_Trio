package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class StartPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_page)
        var start=findViewById<ImageView>(R.id.StartButton)
        start.setOnClickListener{
            start.animate().apply{
                this.setDuration(1000)
                this.translationXBy(5000f)
            }.withEndAction{
                startActivity(Intent(this,InfoPage::class.java))
            }
        }
    }
}