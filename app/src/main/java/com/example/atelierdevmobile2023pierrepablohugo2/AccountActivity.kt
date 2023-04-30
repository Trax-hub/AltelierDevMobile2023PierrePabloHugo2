package com.example.atelierdevmobile2023pierrepablohugo2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.zxing.integration.android.IntentIntegrator

class AccountActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        setHeaderTitle("Création de compte")

        val button = findViewById<LinearLayoutCompat>(R.id.button_qr_code)
        button.setOnClickListener(View.OnClickListener {
            val scanner = IntentIntegrator(this)
            scanner.setOrientationLocked(true)
            scanner.setPrompt("Scan QR Code")
            scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()
        })

        val button2 = findViewById<AppCompatButton>(R.id.button_form)
        button2.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application, RegisterActivity::class.java)
            startActivity(newIntent)
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(intentResult != null){
            val contents = intentResult.contents
            if(contents != null){
                val registerActivity = Intent(application, RegisterActivity::class.java)
                registerActivity.putExtra("user", contents)
                startActivity(registerActivity)
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}