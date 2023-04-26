package com.example.atelierdevmobile2023pierrepablohugo2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton

class AccountActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        setHeaderTitle("Création de compte")

        val button = findViewById<AppCompatButton>(R.id.button_qr_code)
        button.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application, QRCodeActivity::class.java)
            startActivity(newIntent)
        })

        val button2 = findViewById<AppCompatButton>(R.id.button_form)
        button2.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application, RegisterActivity::class.java)
            startActivity(newIntent)
        })
    }
}