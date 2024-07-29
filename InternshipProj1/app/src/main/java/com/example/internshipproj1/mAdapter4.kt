package com.example.internshipproj1

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class mAdapter4(private var list:ArrayList<CartItem>) : RecyclerView.Adapter<mAdapter4.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType : Int): mAdapter4.MyViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row3, parent, false)
        return  MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: mAdapter4.MyViewHolder, position: Int) {
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
            var mAuth= FirebaseAuth.getInstance()
            var dbref= FirebaseDatabase.getInstance().getReference("Order_History").child(mAuth.currentUser?.email.toString().split("@")[0])
            dbref.child(prod.ID.toString()).removeValue()
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var Name = itemView.findViewById<TextView>(R.id.Name)
        var Price = itemView.findViewById<TextView>(R.id.Price)
        var Image=itemView.findViewById<ImageView>(R.id.picture)
        var Cancel=itemView.findViewById<Button>(R.id.cancel)
    }
}