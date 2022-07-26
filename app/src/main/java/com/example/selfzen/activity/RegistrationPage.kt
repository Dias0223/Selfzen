package com.example.selfzen.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.selfzen.R
import com.example.selfzen.model.Customer
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registration_page.*


class RegistrationPage : AppCompatActivity() {

    private lateinit var custName: EditText
    private lateinit var custEmail : EditText
    private lateinit var custTP : EditText
    private lateinit var submit : Button

    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_page)

        custName = findViewById(R.id.txt_custname)
        custEmail = findViewById(R.id.txt_custemail)
        custTP= findViewById(R.id.txt_custtp)
        submit = findViewById(R.id.btn_submit)

        dbRef = FirebaseDatabase.getInstance().getReference("CustomerData")

        btn_submit.setOnClickListener{

            val CustName = custName.text.toString()
            val CustEmail = custEmail.text.toString()
            val CustMobileNo = custTP.text.toString()

            if(CustName.isEmpty()){
                custName.error ="Please Enter Your Name"
            }
            if(CustEmail.isEmpty()){
                custEmail.error="Please Enter Your Email Address"
            }
            if(CustMobileNo.isEmpty()){
                custTP.error="Please Enter Your Mobile No"
            }else{

                val customer = Customer(CustName,CustEmail,CustMobileNo)
                dbRef.child(CustMobileNo).setValue(customer)
                    .addOnCompleteListener {
                        Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_LONG).show()

                        custName.text.clear()
                        custEmail.text.clear()
                        custTP.text.clear()

                    }.addOnFailureListener { err ->
                        Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                    }
            }
        }

    }


}