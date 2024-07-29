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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ReceiptPage : AppCompatActivity() {
    var total=0
    lateinit var myRef: DatabaseReference
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_page)
        Toast.makeText(this, "Take a Screenshot here in case of Refund Request", Toast.LENGTH_LONG).show()
        //Fill the Bill Texts, and The homepage button
        var tohome=findViewById<Button>(R.id.ToHome)
        tohome.setOnClickListener{
            myRef = FirebaseDatabase.getInstance().getReference("Cart")
                .child(mAuth.currentUser?.email.toString().split("@")[0])
            myRef.removeValue()
            startActivity(Intent(this,Home::class.java))
        }
        var theBill = findViewById<TextView>(R.id.TheBill1)
        var theBill2 = findViewById<TextView>(R.id.TheBill2)
        mAuth = FirebaseAuth.getInstance()
        fillBill(theBill, theBill2)

    }

    private fun fillBill(theBill: TextView?, theBill2: TextView?) {
        myRef= FirebaseDatabase.getInstance().getReference("Cart").child(mAuth.currentUser!!.email.toString().split("@")[0])
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snap in dataSnapshot.children) {
                        val value = snap.getValue(CartItem::class.java)
                        theBill?.setText(theBill.text.toString() + "\n" + value?.Name)
                        theBill2?.setText(theBill2.text.toString() + "\n" + value?.Price)
                        total=total+value?.Price.toString().toInt()
                    }
                    //Got the total from there too, so display it
                    var theTotal = findViewById<TextView>(R.id.TheTotal)
                    theTotal.setText("Total: " + total.toString())
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

    }
}