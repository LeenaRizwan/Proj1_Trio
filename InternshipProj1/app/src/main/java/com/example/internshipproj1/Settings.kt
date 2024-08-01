package com.example.internshipproj1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        var gotofeedback=findViewById<Button>(R.id.GoToFeedback)
        var gotosubmitreq=findViewById<Button>(R.id.GoToRefund)
        var gotodelete=findViewById<Button>(R.id.GoToDelete)
        var gotoprof=findViewById<ImageView>(R.id.backtoprof)
        var swtichfororder=findViewById<Switch>(R.id.RecOrderHist)
        swtichfororder.setOnCheckedChangeListener { buttonView, isChecked ->
                val sharedPref =getPreferences(Context.MODE_PRIVATE)
                with (sharedPref.edit()) {
                    putBoolean("SaveOrder", swtichfororder.isChecked)
                    apply()
            }
        }
        gotosubmitreq.setOnClickListener{
            startActivity(Intent(this,SubmitRefundReq::class.java))
        }
        gotoprof.setOnClickListener{
            startActivity(Intent(this,ProfileHome::class.java))
        }
        gotodelete.setOnClickListener{
            startActivity(Intent(this,DeleteAccount::class.java))
        }
        gotofeedback.setOnClickListener{
            startActivity((Intent(this,SubmitFeedback::class.java)))
        }
    }
}