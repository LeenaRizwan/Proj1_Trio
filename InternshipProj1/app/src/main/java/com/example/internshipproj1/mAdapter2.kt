package com.example.internshipproj1

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class mAdapter2 (private var list:ArrayList<Reveiw>) : RecyclerView.Adapter<mAdapter2.MyViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType : Int): mAdapter2.MyViewHolder{
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row2, parent, false)
            return  MyViewHolder(itemView)
        }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val rev: Reveiw = list[position]
        holder.usern.setText(rev.User.toString())
        holder.stars.setText(rev.Stars.toString())
        holder.rev.setText(rev.Rev.toString())
    }


    override fun getItemCount(): Int {
            return list.size
        }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            var usern = itemView.findViewById<TextView>(R.id.user)
            var stars=itemView.findViewById<TextView>(R.id.stars)
            var rev=itemView.findViewById<TextView>(R.id.rev)
    }
}