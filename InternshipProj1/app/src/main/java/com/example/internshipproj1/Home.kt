package com.example.internshipproj1

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class Home : AppCompatActivity() {
var items=0
    var items2=0
    lateinit var itemlist3:ArrayList<Product>
    lateinit var itemlist2:ArrayList<Product>
    lateinit var itemlist:ArrayList<Product>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        itemlist=ArrayList<Product>()
        //Fill first row
        var mAuth= FirebaseAuth.getInstance()
        itemlist2=ArrayList<Product>()
        fillList1(itemlist2)
        //Fill second row
        itemlist3=ArrayList<Product>()
        fillList3(itemlist3)
        //Fill third row
        var Catagory1 = "Tech"
        var MyRef=FirebaseDatabase.getInstance().getReference("Seacrh_History").child(mAuth.currentUser?.email.toString().split("@")[0])
        MyRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snap in dataSnapshot.children) {
                        val value = snap.getValue(String::class.java)
                        if(value?.isEmpty()==false){
                        Catagory1 = value.toString()}
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        fillList2(itemlist,Catagory1)

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

    private fun fillList3(itemlist3: java.util.ArrayList<Product>) {

        var Db= FirebaseDatabase.getInstance()
        var myRef = Db.getReference("Products")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snap in dataSnapshot.children) {
                        val value = snap.getValue(Product::class.java)
                        itemlist3.add(value!!)
                    }
                }
                if(itemlist3.size>0){
                    var title4=findViewById<TextView>(R.id.title4)
                    title4.setText(itemlist3.last().Name)
                    var price4=findViewById<TextView>(R.id.price4)
                    price4.setText(itemlist3.last().Price.toString() + " .Rs")
                    val storef = FirebaseStorage.getInstance().reference.child(itemlist3.last().Name.lowercase() + "_image.png")
                    val localtemp = File.createTempFile("temp", "png")
                    storef.getFile(localtemp).addOnSuccessListener {
                        val bitmap = BitmapFactory.decodeFile(localtemp.absolutePath)
                        var image4=findViewById<ImageView>(R.id.image4).setImageBitmap(bitmap)
                    }
                    itemlist3.removeLast()
                }
                if(itemlist3.size>1){
                    var title5=findViewById<TextView>(R.id.title5)
                    title5.setText(itemlist3.last().Name)
                    var price5=findViewById<TextView>(R.id.price5)
                    price5.setText(itemlist3.last().Price.toString() + " .Rs")
                    val storef = FirebaseStorage.getInstance().reference.child(itemlist3.last().Name.lowercase() + "_image.png")
                    val localtemp = File.createTempFile("temp", "png")
                    storef.getFile(localtemp).addOnSuccessListener {
                        val bitmap = BitmapFactory.decodeFile(localtemp.absolutePath)
                        var image5=findViewById<ImageView>(R.id.image5).setImageBitmap(bitmap)
                    }
                    itemlist3.removeLast()
                }
                if(itemlist3.size>2){
                    var title6=findViewById<TextView>(R.id.title6)
                    title6.setText(itemlist3.last().Name)
                    var price6=findViewById<TextView>(R.id.price6)
                    price6.setText(itemlist3.last().Price.toString() + " .Rs")
                    val storef = FirebaseStorage.getInstance().reference.child(itemlist3.last().Name.lowercase() + "_image.png")
                    val localtemp = File.createTempFile("temp", "png")
                    storef.getFile(localtemp).addOnSuccessListener {
                        val bitmap = BitmapFactory.decodeFile(localtemp.absolutePath)
                        var image6=findViewById<ImageView>(R.id.image6).setImageBitmap(bitmap)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

    private fun fillList1(itemlist2: java.util.ArrayList<Product>) {

        var Db= FirebaseDatabase.getInstance()
        var myRef = Db.getReference("Products")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snap in dataSnapshot.children) {
                        val value = snap.getValue(Product::class.java)
                        if((value?.Sale_Percent!! >0)&&(items2<4)) {
                            itemlist2.add(value!!)
                            items2 += 1
                        }
                    }
                }
                if(items2>0){
                    var title1=findViewById<TextView>(R.id.title1)
                    title1.setText(itemlist2[0].Name)
                    var price1=findViewById<TextView>(R.id.price1)
                    price1.setText(itemlist2[0].Price.toString() + " .Rs")
                    val storef = FirebaseStorage.getInstance().reference.child(itemlist2[0].Name.lowercase() + "_image.png")
                    val localtemp = File.createTempFile("temp", "png")
                    storef.getFile(localtemp).addOnSuccessListener {
                        val bitmap = BitmapFactory.decodeFile(localtemp.absolutePath)
                        var image1=findViewById<ImageView>(R.id.image1).setImageBitmap(bitmap)
                    }
                }
                if(items2>1){
                    var title2=findViewById<TextView>(R.id.title2)
                    title2.setText(itemlist2[1].Name)
                    var price2=findViewById<TextView>(R.id.price2)
                    price2.setText(itemlist2[1].Price.toString() + " .Rs")
                    val storef = FirebaseStorage.getInstance().reference.child(itemlist2[1].Name.lowercase() + "_image.png")
                    val localtemp = File.createTempFile("temp", "png")
                    storef.getFile(localtemp).addOnSuccessListener {
                        val bitmap = BitmapFactory.decodeFile(localtemp.absolutePath)
                        var image2=findViewById<ImageView>(R.id.image2).setImageBitmap(bitmap)
                    }
                }
                if(items2>2){
                    var title3=findViewById<TextView>(R.id.title3)
                    title3.setText(itemlist2[2].Name)
                    var price3=findViewById<TextView>(R.id.price3)
                    price3.setText(itemlist2[2].Price.toString() + " .Rs")
                    val storef = FirebaseStorage.getInstance().reference.child(itemlist2[2].Name.lowercase() + "_image.png")
                    val localtemp = File.createTempFile("temp", "png")
                    storef.getFile(localtemp).addOnSuccessListener {
                        val bitmap = BitmapFactory.decodeFile(localtemp.absolutePath)
                        var image3=findViewById<ImageView>(R.id.image3).setImageBitmap(bitmap)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun fillList2(itemlist: java.util.ArrayList<Product>, catagory1: String) {
                var Db= FirebaseDatabase.getInstance()
                var myRef = Db.getReference("Products")
                myRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (snap in dataSnapshot.children) {
                                val value = snap.getValue(Product::class.java)
                                if(((value?.Catagory2==catagory1)||(value?.Catagory1==catagory1))&&(items<4)) {
                                    itemlist.add(value!!)
                                    items += 1
                                }
                            }
                        }
                        if(items>0){
                            var title7=findViewById<TextView>(R.id.title7)
                            title7.setText(itemlist[0].Name)
                            var price7=findViewById<TextView>(R.id.price7)
                            price7.setText(itemlist[0].Price.toString() + " .Rs")
                            val storef = FirebaseStorage.getInstance().reference.child(itemlist[0].Name.lowercase() + "_image.png")
                            val localtemp = File.createTempFile("temp", "png")
                            storef.getFile(localtemp).addOnSuccessListener {
                                val bitmap = BitmapFactory.decodeFile(localtemp.absolutePath)
                                var image7=findViewById<ImageView>(R.id.image7).setImageBitmap(bitmap)
                            }
                        }
                        if(items>1){
                            var title8=findViewById<TextView>(R.id.title8)
                            title8.setText(itemlist[1].Name)
                            var price8=findViewById<TextView>(R.id.price8)
                            price8.setText(itemlist[1].Price.toString() + " .Rs")
                            val storef = FirebaseStorage.getInstance().reference.child(itemlist[1].Name.lowercase() + "_image.png")
                            val localtemp = File.createTempFile("temp", "png")
                            storef.getFile(localtemp).addOnSuccessListener {
                                val bitmap = BitmapFactory.decodeFile(localtemp.absolutePath)
                                var image8=findViewById<ImageView>(R.id.image8).setImageBitmap(bitmap)
                            }
                        }
                        if(items>2){
                            var title9=findViewById<TextView>(R.id.title9)
                            title9.setText(itemlist[2].Name)
                            var price9=findViewById<TextView>(R.id.price9)
                            price9.setText(itemlist[2].Price.toString() + " .Rs")
                            val storef = FirebaseStorage.getInstance().reference.child(itemlist[2].Name.lowercase() + "_image.png")
                            val localtemp = File.createTempFile("temp", "png")
                            storef.getFile(localtemp).addOnSuccessListener {
                                val bitmap = BitmapFactory.decodeFile(localtemp.absolutePath)
                                var image9=findViewById<ImageView>(R.id.image9).setImageBitmap(bitmap)
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                    }
                })
            }
        }