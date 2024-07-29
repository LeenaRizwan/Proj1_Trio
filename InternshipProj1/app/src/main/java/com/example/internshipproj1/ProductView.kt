package com.example.internshipproj1

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class ProductView : AppCompatActivity() {
    var prod=Product()
    lateinit var revlist:ArrayList<Reveiw>
    lateinit var itemlist:ArrayList<Product>
    lateinit var madapter: mAdapter2
    lateinit var rev: RecyclerView
    var items=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_view)
        prod.Name = intent.getStringExtra("Name").toString()
        prod.Catagory1 = intent.getStringExtra("Catagory1").toString()
        prod.Catagory2 = intent.getStringExtra("Catagory2").toString()
        prod.Desc = intent.getStringExtra("Desc").toString()
        prod.Stars = intent.getIntExtra("Stars", -1)
        prod.Colour = intent.getStringExtra("Colour").toString()
        prod.Price = intent.getIntExtra("Price", 0)
        prod.Sale_Percent = intent.getIntExtra("Sale", 0)
        prod.Sold = intent.getIntExtra("Sold", 0)
        prod.Stock = intent.getIntExtra("Stock", 0)
        prod.ID=intent.getIntExtra("ID",0)
        //FILLING REVEIWS RECYCLER VEIW
        rev = findViewById<RecyclerView>(R.id.rev)
        rev.layoutManager = LinearLayoutManager(this)
        rev.setHasFixedSize(true)
        revlist = ArrayList<Reveiw>()
        fillList(revlist, prod.Name)
        //FILLING EVERY OTHER TEXT VEIW-remember to make space for EVERY piece data later
        var title = findViewById<TextView>(R.id.titlehere)
        title.setText(prod.Name)
        var price = findViewById<TextView>(R.id.pricehere)
        price.setText(prod.Price.toString() + " .Rs")
        var stars = findViewById<TextView>(R.id.starshere)
        stars.setText(prod.Stars.toString())
        var desc = findViewById<TextView>(R.id.deschere)
        desc.setText(prod.Desc)
        //itemlist for Recommended Products
        itemlist = ArrayList<Product>()
        fillList2(itemlist, prod.Catagory1, prod.Catagory2)
        //Clickable recc products
        var LL1 = findViewById<LinearLayout>(R.id.LL1)
        LL1.setOnClickListener {
            var intent = Intent(this, ProductView::class.java)
            intent.putExtra("Name", itemlist[0].Name)
            intent.putExtra("Catagory1", itemlist[0].Catagory1)
            intent.putExtra("Catagory2", itemlist[0].Catagory2)
            intent.putExtra("Desc", itemlist[0].Desc)
            intent.putExtra("Colour", itemlist[0].Colour)
            intent.putExtra("Price", itemlist[0].Price)
            intent.putExtra("Sale", itemlist[0].Sale_Percent)
            intent.putExtra("Sold", itemlist[0].Sold)
            intent.putExtra("Stock", itemlist[0].Stock)
            intent.putExtra("Stars", itemlist[0].Stars)
            intent.putExtra("ID", itemlist[0].ID)
            ContextCompat.startActivity(this, intent, null)
        }
        var LL2 = findViewById<LinearLayout>(R.id.LL2)
        LL2.setOnClickListener {
            var intent = Intent(this, ProductView::class.java)
            intent.putExtra("Name", itemlist[1].Name)
            intent.putExtra("Catagory1", itemlist[1].Catagory1)
            intent.putExtra("Catagory2", itemlist[1].Catagory2)
            intent.putExtra("Desc", itemlist[1].Desc)
            intent.putExtra("Colour", itemlist[1].Colour)
            intent.putExtra("Price", itemlist[1].Price)
            intent.putExtra("Sale", itemlist[1].Sale_Percent)
            intent.putExtra("Sold", itemlist[1].Sold)
            intent.putExtra("Stock", itemlist[1].Stock)
            intent.putExtra("Stars", itemlist[1].Stars)
            intent.putExtra("ID", itemlist[1].ID)
            ContextCompat.startActivity(this, intent, null)
        }
        var LL3 = findViewById<LinearLayout>(R.id.LL3)
        LL3.setOnClickListener {
            var intent = Intent(this, ProductView::class.java)
            intent.putExtra("Name", itemlist[2].Name)
            intent.putExtra("Catagory1", itemlist[2].Catagory1)
            intent.putExtra("Catagory2", itemlist[2].Catagory2)
            intent.putExtra("Desc", itemlist[2].Desc)
            intent.putExtra("Colour", itemlist[2].Colour)
            intent.putExtra("Price", itemlist[2].Price)
            intent.putExtra("Sale", itemlist[2].Sale_Percent)
            intent.putExtra("Sold", itemlist[2].Sold)
            intent.putExtra("Stock", itemlist[2].Stock)
            intent.putExtra("Stars", itemlist[2].Stars)
            intent.putExtra("ID", itemlist[2].ID)
            ContextCompat.startActivity(this, intent, null)
        }

        var submitbutton = findViewById<Button>(R.id.submitrev)
        submitbutton.setOnClickListener {
        var database = FirebaseDatabase.getInstance()
        var mAuth = FirebaseAuth.getInstance()
        var reveiw1 = findViewById<EditText>(R.id.reveiw)
        var stars1 = findViewById<EditText>(R.id.starrev)
        var tocart=findViewById<Button>(R.id.tocart)
        tocart.setOnClickListener{
                var databaseRef=database.getReference("Products").child(prod.ID.toString()).child("Stock")
                if(prod.Stock==0){
                    //Toast to say theyre outta stock
                }
                else{
                    //go -1 stock and +1 Sold
                    databaseRef.setValue(prod.Stock-1)
                    prod.Stock=prod.Stock-1
                    databaseRef.setValue(prod.Sold+1)
                    prod.Stock=prod.Sold+1
                    //then add to Cart, Username, Name-Price
                    databaseRef=database.getReference("Cart").child(mAuth.currentUser?.email.toString().split("@")[0])
                    databaseRef.child(prod.ID.toString()).child("Name").setValue(prod.Name)
                    databaseRef.child(prod.ID.toString()).child("Price").setValue(prod.Price)
                    databaseRef.child(prod.ID.toString()).child("ID").setValue(prod.ID)
                    //Cart mein use search wali row and mAdapter
                    startActivity(Intent(this,CartHome::class.java))
                }
            }
        if ((stars1.text.toString().toInt() < 6) && (stars1.text.toString().toInt() > -1)) {
            var databaseRef = database.getReference("Reveiws")
                .child(prod.Name + "_" + mAuth.currentUser?.email.toString().split("@")[0])
            databaseRef.child("Rev").setValue(reveiw1.text.toString())
            databaseRef.child("User").setValue(mAuth.currentUser?.email.toString().split("@")[0])
            databaseRef.child("Stars").setValue(stars1.text.toString().toInt())
            databaseRef.child("Item").setValue(prod.Name)
            var Rev1=Reveiw(stars1.text.toString().toInt(),reveiw1.text.toString(),mAuth.currentUser?.email.toString().split("@")[0],prod.Name)

            prod.Stars=(prod.Stars * revlist.size) + stars1.text.toString().toInt()
            revlist.add(Rev1)
            prod.Stars=prod.Stars / revlist.size
            stars.setText(prod.Stars.toString())
            databaseRef=database.getReference("Products").child(prod.ID.toString()).child("Stars")
            databaseRef.setValue(prod.Stars)
        } else {
            var errorm = findViewById<TextView>(R.id.errormessage)
            errorm.visibility = View.VISIBLE
            errorm.setText("Please input a number of stars between 0 and 5!")
        }


    }




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

    private fun fillList2(itemlist: java.util.ArrayList<Product>, Cat1:String,Cat2:String) {
        var Db= FirebaseDatabase.getInstance()
        var myRef = Db.getReference("Products")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snap in dataSnapshot.children) {
                        val value = snap.getValue(Product::class.java)
                        if(((value?.Catagory2==Cat1)||(value?.Catagory2==Cat2)||(value?.Catagory1==Cat1)||(value?.Catagory1==Cat2))&&(items<4)&&(value?.Name!=prod.Name)) {
                            itemlist.add(value!!)
                            items += 1
                        }
                    }
                }
                if(items>0){
                    var LL1=findViewById<LinearLayout>(R.id.LL1)
                    LL1.visibility=View.VISIBLE
                    var title1=findViewById<TextView>(R.id.title1)
                    title1.setText(itemlist[0].Name)
                    var price1=findViewById<TextView>(R.id.price1)
                    price1.setText(itemlist[0].Price.toString() + " .Rs")
                    val storef = FirebaseStorage.getInstance().reference.child(itemlist[0].Name.lowercase() + "_image.png")
                    val localtemp = File.createTempFile("temp", "png")
                    storef.getFile(localtemp).addOnSuccessListener {
                        val bitmap = BitmapFactory.decodeFile(localtemp.absolutePath)
                        var image1=findViewById<ImageView>(R.id.image1).setImageBitmap(bitmap)
                    }
                }
                if(items>1){
                    var LL2=findViewById<LinearLayout>(R.id.LL2)
                    LL2.visibility=View.VISIBLE
                    var title2=findViewById<TextView>(R.id.title2)
                    title2.setText(itemlist[1].Name)
                    var price2=findViewById<TextView>(R.id.price2)
                    price2.setText(itemlist[1].Price.toString() + " .Rs")
                    val storef = FirebaseStorage.getInstance().reference.child(itemlist[1].Name.lowercase() + "_image.png")
                    val localtemp = File.createTempFile("temp", "png")
                    storef.getFile(localtemp).addOnSuccessListener {
                        val bitmap = BitmapFactory.decodeFile(localtemp.absolutePath)
                        var image2=findViewById<ImageView>(R.id.image2).setImageBitmap(bitmap)
                    }
                }
                if(items>2){
                    var LL3=findViewById<LinearLayout>(R.id.LL3)
                    LL3.visibility=View.VISIBLE
                    var title3=findViewById<TextView>(R.id.title3)
                    title3.setText(itemlist[2].Name)
                    var price3=findViewById<TextView>(R.id.price3)
                    title3.setText(itemlist[2].Price.toString() + " .Rs")
                    val storef = FirebaseStorage.getInstance().reference.child(itemlist[2].Name.lowercase() + "_image.png")
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

    private fun fillList(revlist: java.util.ArrayList<Reveiw>, Name:String) {
        var Db= FirebaseDatabase.getInstance()
        var myRef = Db.getReference("Reveiws")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    revlist.clear()
                    for (snap in dataSnapshot.children) {
                        val value = snap.getValue(Reveiw::class.java)
                        if(value?.Item==Name){
                        revlist.add(value!!)}
                    }
                    madapter = mAdapter2(revlist)
                    rev.adapter = madapter
                    rev.visibility = View.VISIBLE
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}