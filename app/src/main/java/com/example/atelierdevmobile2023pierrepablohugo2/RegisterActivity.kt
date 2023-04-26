package com.example.atelierdevmobile2023pierrepablohugo2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton

class RegisterActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        showBack()
        setHeaderTitle("Création de compte")

        var createButton = findViewById<AppCompatButton>(R.id.create_button)
        createButton.setOnClickListener(View.OnClickListener {
            val newIntent = Intent(application, MainActivity::class.java)
            finishAffinity()
            startActivity(newIntent)
        })
    }
}