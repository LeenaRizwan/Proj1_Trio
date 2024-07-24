package com.example.internshipproj1
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getContextForLanguage
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import java.io.File


class mAdapter(private var list:ArrayList<Product>, private var con:Context) : RecyclerView.Adapter<mAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType : Int): mAdapter.MyViewHolder{
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return  MyViewHolder(itemView)
    }

    public fun setfilteredlist(list:ArrayList<Product>){
        this.list= list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: mAdapter.MyViewHolder, position: Int) {
        val prod: Product = list[position]
        holder.Name.text = prod.Name
        holder.Price.text = prod.Price.toString() + " Rs"
        var imageName = prod.Name.lowercase() + "_image.png"
        val storef = FirebaseStorage.getInstance().reference.child(imageName)
        val localtemp = File.createTempFile("temp", "png")
        storef.getFile(localtemp).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localtemp.absolutePath)
            holder.Image.setImageBitmap(bitmap)
        }
        holder.ProdButton.setOnClickListener{
            intentTime(prod)
        }
    }

    private fun intentTime(prod: Product): Intent {
        var intent=Intent(con, ProductView::class.java)
        intent.putExtra("Name", prod.Name)
        intent.putExtra("Catagory1", prod.Catagory1)
        intent.putExtra("Catagory2", prod.Catagory2)
        intent.putExtra("Desc", prod.Desc)
        intent.putExtra("Colour", prod.Colour)
        intent.putExtra("Price", prod.Price)
        intent.putExtra("Sale", prod.Sale_Percent)
        intent.putExtra("Sold", prod.Sold)
        intent.putExtra("Stock", prod.Stock)
        intent.putExtra("Stars",prod.Stars)
        startActivity(con, intent, null)
        return intent
    }


    override fun getItemCount(): Int {
        return list.size
    }

    public class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var Name = itemView.findViewById<TextView>(R.id.Name)
        var Price=itemView.findViewById<TextView>(R.id.Price)
        var Image=itemView.findViewById<ImageView>(R.id.picture)
        var ProdButton=itemView.findViewById<Button>(R.id.prodbutton)
    }
}