package com.example.selfzen.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.example.selfzen.R
import kotlinx.android.synthetic.main.activity_add_item.btn_next
import kotlinx.android.synthetic.main.activity_add_item_name.*

private const val CAMERA_REQUEST_CODE=101
class AddItemName : AppCompatActivity() {

    private lateinit var mobileNo : TextView
    private lateinit var codeScanner: CodeScanner
    private lateinit var ItemName : TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item_name)

        setupPermissions()
        codeScanner()

        mobileNo = findViewById(R.id.txt_mobileno)
        val MobileNo = intent.getStringExtra("MobileNo")
        mobileNo.text = "Customer ID : "+MobileNo
        ItemName = findViewById(R.id.tv_textview)

        btn_next.setOnClickListener{
            val ProName = ItemName.text.toString()
        if (ProName.isEmpty()){
            ItemName.error = "Please Scan Name Tage"
        }else {
            startActivity(
                Intent(this, AddItem::class.java)
                    .putExtra("MobileNo", mobileNo.text.toString())
                    .putExtra("proName", ItemName.text.toString())
            )
        }

        }
        btn_cart.setOnClickListener{
            startActivity(
                Intent(this, CartPage::class.java)
                    .putExtra("MobileNo", mobileNo.text.toString()))
        }
    }

    private fun codeScanner() {
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)


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
                    ItemName.text = it.text
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
                    Toast.makeText(this,"You need the camera permission to be able to use this app",
                        Toast.LENGTH_SHORT).show()
                }else{
                    //SUCCESSFULLY
                }
            }
        }
    }


}