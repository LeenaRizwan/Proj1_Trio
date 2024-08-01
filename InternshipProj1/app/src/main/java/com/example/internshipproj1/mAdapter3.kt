package com.example.internshipproj1

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.snapshots
import com.google.firebase.database.values
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class mAdapter3(private var list:ArrayList<CartItem>) :RecyclerView.Adapter<mAdapter3.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType : Int): mAdapter3.MyViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row3, parent, false)
        return  MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: mAdapter3.MyViewHolder, position: Int) {
        val prod: CartItem = list[position]
        holder.Name.text = prod.Name
        holder.Price.text = prod.Price.toString() + " Rs"
        var imageName = prod.Name.lowercase() + "_image.png"
        val storef = FirebaseStorage.getInstance().reference.child(imageName)
        val localtemp = File.createTempFile("temp", "png")
        storef.getFile(localtemp).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localtemp.absolutePath)
            holder.Image.setImageBitmap(bitmap)
        }
        holder.Cancel.setOnClickListener{
            list.remove(prod)
            var mAuth=FirebaseAuth.getInstance()
            var dbref= FirebaseDatabase.getInstance().getReference("Cart").child(mAuth.currentUser?.email.toString().split("@")[0])
            dbref.child(prod.ID.toString()).removeValue()
            dbref=FirebaseDatabase.getInstance().getReference("Products").child(prod.ID.toString())
            dbref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                            val value = dataSnapshot.getValue(Product::class.java)
                            value?.Stock=value?.Stock!! + 1
                            value?.Sold= value?.Sold!! - 1
                        dbref.child("Stock").setValue(value.Stock)
                        dbref.child("Sold").setValue(value.Sold)
                    }

                }
                override fun onCancelled(error: DatabaseError) {
                }
            })
        }
    }



    override fun getItemCount(): Int {
        return list.size
    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var Name = itemView.findViewById<TextView>(R.id.Name)
        var Price = itemView.findViewById<TextView>(R.id.Price)
        var Image=itemView.findViewById<ImageView>(R.id.picture)
        var Cancel=itemView.findViewById<ImageView>(R.id.cancel)
    }
}