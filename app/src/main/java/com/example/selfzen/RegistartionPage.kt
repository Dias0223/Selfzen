package com.example.selfzen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegistartionPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registarion_page)

        val submit = findViewById<Button>(R.id.btn_submit)

        submit.setOnClickListener{

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}