package com.example.selfzen.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.selfzen.R

class MainActivity : AppCompatActivity() {

    private lateinit var mobileNo : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mobileNo= findViewById(R.id.txt_TP)

        val regPageLink = findViewById<TextView>(R.id.txt_register)
        val btn_start = findViewById<Button>(R.id.btn_sign)

        regPageLink.setOnClickListener{
            val intent = Intent(this, RegistrationPage::class.java)
            startActivity(intent)
        }
        btn_start.setOnClickListener{
            val MobileNo = mobileNo.text.toString()
            if(MobileNo.isEmpty()){
                mobileNo.error ="Please Enter Registered Mobile No"
            }else {
                startActivity(
                    Intent(this, AddItemName::class.java)
                        .putExtra("MobileNo", mobileNo.text.toString())
                )
            }
        }
    }
}