package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OrderHistory : AppCompatActivity() {
    lateinit var cartlist: ArrayList<CartItem>
    lateinit var cartrev:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        cartrev=findViewById<RecyclerView>(R.id.cartrev)
        cartrev.layoutManager= LinearLayoutManager(this)
        cartlist=ArrayList<CartItem>()
        //Okay so
        fillList(cartlist)
        cartrev.adapter=mAdapter4(cartlist)
        cartrev.visibility = View.VISIBLE
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

    private fun fillList(cartlist: java.util.ArrayList<CartItem>) {
        var Db= FirebaseDatabase.getInstance()
        var mAuth= FirebaseAuth.getInstance()
        var myRef = Db.getReference("Order_History").child(mAuth.currentUser?.email.toString().split("@")[0])
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    cartlist.clear()
                    for (snap in dataSnapshot.children) {
                        val value = snap.getValue(CartItem::class.java)
                        cartlist.add(value!!)
                    }
                    cartrev.adapter=mAdapter4(cartlist)
                    cartrev.visibility = View.VISIBLE
                }

            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}