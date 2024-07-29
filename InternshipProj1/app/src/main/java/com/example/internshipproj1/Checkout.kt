package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Checkout : AppCompatActivity() {
    var total=0
    lateinit var myRef:DatabaseReference
    lateinit var mAuth:FirebaseAuth
    lateinit var cartlist:ArrayList<CartItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        //Fill the Bill Screen
        var theBill = findViewById<TextView>(R.id.TheBill1)
        var theBill2 = findViewById<TextView>(R.id.TheBill2)
        mAuth = FirebaseAuth.getInstance()
        cartlist=ArrayList<CartItem>()
        fillBill(theBill, theBill2)
        //Get Current Address and put it in the Edit Text
        var theAddr = findViewById<EditText>(R.id.Address)
        myRef = FirebaseDatabase.getInstance().getReference("Users")
            .child(mAuth.currentUser?.email.toString().split("@")[0])
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val value = dataSnapshot.getValue(User::class.java)
                    theAddr.setText(value?.Address)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        //The Switch
        var payonline = findViewById<Switch>(R.id.OnlinePay)
        var LL3 = findViewById<LinearLayout>(R.id.LL3)
        payonline.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                LL3.visibility = View.VISIBLE
            } else {
                LL3.visibility = View.GONE
            }
        }
        //Confirm Button, empty cart
        var EndButton = findViewById<Button>(R.id.ConfirmOrder)
        EndButton.setOnClickListener {
            var credcard=findViewById<EditText>(R.id.CredCard)
            //Maybe make an error message if ischecked and isnt filled
            if((payonline.isChecked==true)&&(credcard.text.isEmpty())){
                var em=findViewById<TextView>(R.id.ErrorM)
                em.setText("You Must fill In your credit card info before making your order.")
            }
            else {
                //Fill Into Order History!
                myRef = FirebaseDatabase.getInstance().getReference("Order_History")
                    .child(mAuth.currentUser?.email.toString().split("@")[0])
                for( i in cartlist){
                    myRef.child(i.ID.toString()).child("Name").setValue(i.Name)
                    myRef.child(i.ID.toString()).child("Price").setValue(i.Price)
                    myRef.child(i.ID.toString()).child("ID").setValue(i.ID)
                }

                Toast.makeText(this, "Order Confirmed!", Toast.LENGTH_SHORT).show()
                var intent =Intent(this,ReceiptPage::class.java)
                startActivity(intent)
            }
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

    private fun fillBill(theBill: TextView?, theBill2: TextView?) {
        myRef=FirebaseDatabase.getInstance().getReference("Cart").child(mAuth.currentUser!!.email.toString().split("@")[0])
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snap in dataSnapshot.children) {
                        val value = snap.getValue(CartItem::class.java)
                        cartlist.add(value!!)
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