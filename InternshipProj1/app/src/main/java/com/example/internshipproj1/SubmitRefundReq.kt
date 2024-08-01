package com.example.internshipproj1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage

class SubmitRefundReq : AppCompatActivity() {
    lateinit var ImageUri : Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_refund_req)
        var gotoprof=findViewById<ImageView>(R.id.backtoprof)
        gotoprof.setOnClickListener{
            startActivity(Intent(this,ProfileHome::class.java))
        }
        var submitbutton=findViewById<Button>(R.id.Submitbutton)
        var storageRef = FirebaseStorage.getInstance().getReference()
        submitbutton.setOnClickListener{
         val intent=Intent()
            intent.type="images/"
            intent.action=Intent.ACTION_GET_CONTENT
            startActivityForResult(intent,100)

        }

        fun onActivityResult(requestCode:Int, resultCode:Int,data:Intent?){
            super.onActivityResult(requestCode,resultCode,data)
            if(requestCode==100 && resultCode == RESULT_OK){
                ImageUri = data?.data!!
                val mAuth= FirebaseAuth.getInstance()
                val storRef=FirebaseStorage.getInstance().getReference("images/"+mAuth.currentUser?.email.toString())
                storRef.putFile(ImageUri).addOnSuccessListener {
                    Toast.makeText(this, "Successfully uploaded!", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
                    Toast.makeText(this, "Unsuccessfully uploaded.", Toast.LENGTH_SHORT).show()}
            }
        }

    }
}