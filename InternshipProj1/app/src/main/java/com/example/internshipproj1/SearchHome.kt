package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView

class SearchHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_home)
        var gotofood=findViewById<Button>(R.id.GoToFood)
        var gotohelth=findViewById<Button>(R.id.GoToHealth)
        var gototech=findViewById<Button>(R.id.GoToTech)
        var searchv=findViewById<androidx.appcompat.widget.SearchView>(R.id.search)
        searchv.setOnClickListener{
            var intent=Intent(this, SearchRes::class.java).putExtra("SearchItem", "Null")
            startActivity(intent)
        }
        gototech.setOnClickListener{
            var intent=Intent(this, SearchRes::class.java).putExtra("SearchItem", "Tech")
            startActivity(intent)
        }
        gotohelth.setOnClickListener{
            var intent=Intent(this, SearchRes::class.java).putExtra("SearchItem", "Health")
            startActivity(intent)
        }
        gotofood.setOnClickListener{
            var intent=Intent(this, SearchRes::class.java).putExtra("SearchItem", "Food")
            startActivity(intent)
        }


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