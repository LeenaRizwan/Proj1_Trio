package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class EditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        //Make vars for the fields
        //get the Intent, fill them
        //Go back and put shit in the Intent
        //Test
        var name=findViewById<EditText>(R.id.Name)
        var email=findViewById<EditText>(R.id.Email)
        var passw=findViewById<EditText>(R.id.Password)
        var addr=findViewById<EditText>(R.id.Addr)
        name.setText(intent.getStringExtra("Username"))
        email.setText(intent.getStringExtra("Email"))
        passw.setText(intent.getStringExtra("Password"))
        addr.setText(intent.getStringExtra("Address"))
        var editbutton=findViewById<Button>(R.id.Editinfo)
        editbutton.setOnClickListener{
            var database = FirebaseDatabase.getInstance()
            var databaseRef = database.getReference("users")
            databaseRef.child(email.text.toString().split("@")[0]).child("Email").setValue(email.text.toString())
            databaseRef.child(email.text.toString().split("@")[0]).child("Name").setValue(name.text.toString())
            databaseRef.child(email.text.toString().split("@")[0]).child("Password").setValue(passw.text.toString())
            databaseRef.child(email.text.toString().split("@")[0]).child("DoB").setValue(intent.getStringExtra("DoB"))
            databaseRef.child(email.text.toString().split("@")[0]).child("Address").setValue(addr.text.toString())

            var mAuth=FirebaseAuth.getInstance()
            mAuth.currentUser?.updatePassword(passw.text.toString())
            startActivity(Intent(this, ProfileHome::class.java))
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