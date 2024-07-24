package com.example.internshipproj1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProductView : AppCompatActivity() {
    var prod=Product()
    lateinit var revlist:ArrayList<Reveiw>
    lateinit var madapter: mAdapter2
    lateinit var rev: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_view)
        prod.Name= intent.getStringExtra("Name").toString()
        prod.Catagory1=intent.getStringExtra("Catagory1").toString()
        prod.Catagory2=intent.getStringExtra("Catagory2").toString()
        prod.Desc=intent.getStringExtra("Desc").toString()
        prod.Stars=intent.getIntExtra("Stars",-1)
        prod.Colour=intent.getStringExtra("Colour").toString()
        prod.Price=intent.getIntExtra("Price",0)
        prod.Sale_Percent=intent.getIntExtra("Sale",0)
        prod.Sold=intent.getIntExtra("Sold",0)
        prod.Stock=intent.getIntExtra("Stock",0)
            //FILLING REVEIWS RECYCLER VEIW
        rev=findViewById<RecyclerView>(R.id.rev)
        rev.layoutManager= LinearLayoutManager(this)
        rev.setHasFixedSize(true)
        revlist= ArrayList<Reveiw>()
        fillList(revlist)
            //FILLING EVERY OTHER TEXT VEIW-remember to make space for EVERY piece data later
        var title=findViewById<TextView>(R.id.titlehere)
        title.setText(prod.Name)
        var price=findViewById<TextView>(R.id.pricehere)
        price.setText(prod.Price.toString() + " .Rs")
        var stars=findViewById<TextView>(R.id.starshere)
        stars.setText(prod.Stars.toString())
        var desc=findViewById<TextView>(R.id.deschere)
        desc.setText(prod.Desc)
        //step1: Make the items list in the database--
        //step2: Make the Kt DATA class--
        //step3: row xml--
        //step4: adapter--
        //step5: recyclerveiw
        //step6: extra functions if needed in adapter



        //TO DO:
        //Make the reveiw rows a bit wider, the desc a bit smaller
        //Add the option to write a reveiw, do not allow if already written, above the recycler
        //Make a list of other products in the catagory
        //use a function to display their images and titles and prices, horizontal


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

    private fun fillList(revlist: java.util.ArrayList<Reveiw>) {
        var Db= FirebaseDatabase.getInstance()
        var myRef = Db.getReference("Reveiws")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snap in dataSnapshot.children) {
                        val value = snap.getValue(Reveiw::class.java)
                        revlist.add(value!!)
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