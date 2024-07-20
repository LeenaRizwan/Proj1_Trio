package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //ACTION BAR
        var home=findViewById<Button>(R.id.HomeButton)
        var search=findViewById<Button>(R.id.SearchButton)
        var cart=findViewById<Button>(R.id.CartButton)
        var prof=findViewById<Button>(R.id.ProfileButton)
        home.setOnClickListener{
            startActivity(Intent(this,Home::class.java))
        }
        search.setOnClickListener{
            startActivity(Intent(this,SearchHome::class.java))
        }
        cart.setOnClickListener{
            startActivity(Intent(this,CartHome::class.java))
        }
        prof.setOnClickListener{
            startActivity(Intent(this,ProfileHome::class.java))
        }
    }
}