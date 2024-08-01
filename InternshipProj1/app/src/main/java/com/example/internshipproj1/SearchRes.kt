package com.example.internshipproj1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchRes : AppCompatActivity() {
lateinit var itemList:ArrayList<Product>
lateinit var madapter: mAdapter
    lateinit var rv:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_res)
        rv=findViewById<RecyclerView>(R.id.recv)
        var searchv=findViewById<androidx.appcompat.widget.SearchView>(R.id.searchv)
        rv.layoutManager=LinearLayoutManager(this)
        rv.setHasFixedSize(true)
        itemList= ArrayList<Product>()
        fillList(itemList)


        searchv.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query:String?):Boolean{
                return false
            }
            override fun onQueryTextChange(newText:String?):Boolean{
                filterlist(newText)
                return true
            }
        })
        //ACTION BAR
        var home=findViewById<Button>(R.id.HomeButton)
        var search=findViewById<Button>(R.id.SearchButton)
        var cart=findViewById<Button>(R.id.CartButton)
        var prof=findViewById<Button>(R.id.ProfileButton)
        home.setOnClickListener{
            startActivity(Intent(this,Home::class.java))
        }
        search.setOnClickListener{
            startActivity(Intent(this, SearchHome::class.java))
        }
        cart.setOnClickListener{
            startActivity(Intent(this,CartHome::class.java))
        }
        prof.setOnClickListener{
            startActivity(Intent(this,ProfileHome::class.java))
        }

    }

    private fun filterlist(query:String?) {
        if (query!!.isNotEmpty()) {
            val filteredList = ArrayList<Product>()
            for (i in itemList) {
                if (i.Name.lowercase().contains(query.toString().lowercase()) || i.Catagory1.lowercase()
                        .contains(query.toString().lowercase()) || (i.Catagory2.lowercase()
                        .contains(query.toString().lowercase()))
                ) {
                    filteredList.add(i)
                }
                    madapter.setfilteredlist(filteredList)
            }
        }
        else{
            madapter.setfilteredlist(itemList)
        }
    }

    private fun fillList(itemList: ArrayList<Product>) {
        var Db= FirebaseDatabase.getInstance()
        var s=intent.getStringExtra("SearchItem")
        var myRef = Db.getReference("Products")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (snap in dataSnapshot.children) {
                        val value = snap.getValue(Product::class.java)
                        if((s=="Null")||(s==value?.Catagory1)||(s== value?.Catagory2)){
                        itemList.add(value!!)}
                    }
                    madapter = mAdapter(itemList, this@SearchRes)
                    rv.adapter = madapter
                    rv.visibility = View.VISIBLE
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

    }
}