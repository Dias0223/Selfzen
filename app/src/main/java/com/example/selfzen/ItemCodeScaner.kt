package com.example.selfzen

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

private const val CAMERA_REQUEST_CODE=101

class ItemCodeScaner : AppCompatActivity() {



    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_code_scaner)



        setupPermissions()
        codeScanner()

        val btnCart = findViewById<Button>(R.id.btn_viewCart)

        btnCart.setOnClickListener{

            val intent = Intent(this,CartPage::class.java)
            startActivity(intent)
        }

    }


    private fun codeScanner(){

        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)
        val textView = findViewById<TextView>(R.id.tv_textview)

        codeScanner = CodeScanner(this , scannerView)
        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {

                runOnUiThread{
                    textView.text = it.text
                }
            }
            errorCallback = ErrorCallback {

                runOnUiThread{
                    Log.e("Main" , "Camera initialization error: ${it.message}")
                }
            }
        }

        scannerView.setOnClickListener{
            codeScanner.startPreview()
        }
    }


    override fun onResume(){
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }


    private fun setupPermissions(){
        val permission:Int = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)

        if(permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }
    private fun makeRequest(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE)

    }

    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<out String>,grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            CAMERA_REQUEST_CODE ->{
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"You need the camera permission to be able to use this app",Toast.LENGTH_SHORT).show()
                }else{
                        //Successful
                }
            }
        }
    }
}