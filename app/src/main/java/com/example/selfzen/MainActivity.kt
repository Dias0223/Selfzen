package com.example.selfzen

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val regPageLink = findViewById<TextView>(R.id.txt_regitration)
        val cartButton = findViewById<Button>(R.id.btn_start)

        regPageLink.setOnClickListener {

            val intent = Intent(this,RegistartionPage::class.java)
            startActivity(intent)
        }
        cartButton.setOnClickListener{

            val openMainAct = Intent(this, ItemCodeScaner::class.java);
            startActivity(openMainAct);
        }


    }

}