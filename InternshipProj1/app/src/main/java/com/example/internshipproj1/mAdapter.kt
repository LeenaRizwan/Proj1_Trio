package com.example.internshipproj1
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class mAdapter(private var list:ArrayList<Product>) : RecyclerView.Adapter<mAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType : Int): mAdapter.MyViewHolder{
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return  MyViewHolder(itemView)
    }

    public fun setfilteredlist(list:ArrayList<Product>){
        this.list= list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: mAdapter.MyViewHolder, position: Int){
        val prod:Product=list[position]
        holder.Name.text=prod.Name
        holder.Price.text= prod.Price.toString() + " Rs"
        //Change this image shit asap later, ughh
    }

    override fun getItemCount(): Int {
        return list.size
    }

    public class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var Name = itemView.findViewById<TextView>(R.id.Name)
        var Price=itemView.findViewById<TextView>(R.id.Price)
        var Image=itemView.findViewById<ImageView>(R.id.picture)
    }
}