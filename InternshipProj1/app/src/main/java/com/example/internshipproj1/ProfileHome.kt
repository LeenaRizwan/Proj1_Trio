package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//Cart needs a horizontal Scroll Veiw of the images and Titles of the first three products in the list
//As does buying history, but worry abt those when we get to em
//The pages for them themselves are just Recycler Veiw pages
//Make sure to add ClearAll and delete indiv buttons on the xml
class ProfileHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_home)
        var welcome=findViewById<TextView>(R.id.Welcome)
        var email=findViewById<TextView>(R.id.Email)
        var password=findViewById<TextView>(R.id.Passw)
        var dob=findViewById<TextView>(R.id.DoB)
        var addr=findViewById<TextView>(R.id.Addr)
        var editprof=findViewById<Button>(R.id.EditProf)
        var mAuth= FirebaseAuth.getInstance()
        var database= FirebaseDatabase.getInstance()
        var intent=Intent(this,EditProfile::class.java)
        var myRef = database.getReference("users").child(mAuth.currentUser?.email.toString().split("@")[0])
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(User::class.java)
                intent.putExtra("Username", value?.Name)
                intent.putExtra("Email", value?.Email)
                intent.putExtra("Password", value?.Password)
                intent.putExtra("Address",value?.Address)
                intent.putExtra("DoB", value?.DoB)
                welcome.setText(value?.Name + "'s Buyer Profile")
                email.setText("Email: " + value?.Email)
                password.setText("Password: " + value?.Password)
                dob.setText("Date of Birth: " + value?.DoB)
                addr.setText("Address: " + value?.Address)
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        editprof.setOnClickListener{
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