package com.example.selfzen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_cart_page.*
import java.util.ArrayList

class CartPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_page)

        val dataset: Array<String> = arrayOf("First","Second")
        val adapter = DataAdapter(dataset)
        ItemList.layoutManager=LinearLayoutManager(this)
        ItemList.adapter=adapter

    }


}