package com.example.atelierdevmobile2023pierrepablohugo2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.atelierdevmobile2023pierrepablohugo2.Model.User
import com.example.atelierdevmobile2023pierrepablohugo2.Model.UserStorage
import org.json.JSONObject

class RegisterActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        showBack()
        setHeaderTitle("Création de compte")

        val user = intent.getStringExtra("user")
        if(!user.isNullOrBlank()){
            val userJson = JSONObject(user)
            val user = User(userJson.optString("firstName","not found"),
                userJson.optString("lastName","not found"),
                userJson.optString("email","not found"),
                userJson.optString("address", "not found"),
                userJson.optInt("zipcode"),
                userJson.optString("city","not found"),
                userJson.optInt("cardRef"))

            val name = findViewById<EditText>(R.id.first_name)
            name.setText(user.firstName)
            val lastName = findViewById<EditText>(R.id.last_name)
            lastName.setText(user.lastName)
            val email = findViewById<EditText>(R.id.email)
            email.setText(user.email)
            val address = findViewById<EditText>(R.id.address)
            address.setText(user.address)
            val zipcode = findViewById<EditText>(R.id.zip_code)
            zipcode.setText(user.zipcode.toString())
            val city = findViewById<EditText>(R.id.city)
            city.setText(user.city)
            val cardRef = findViewById<EditText>(R.id.cardRef)
            cardRef.setText(user.cardRef.toString())

        }

        var createButton = findViewById<AppCompatButton>(R.id.create_button)
        createButton.setOnClickListener(View.OnClickListener {

            if(!findViewById<EditText>(R.id.first_name).text.isNullOrBlank()
                && !findViewById<EditText>(R.id.last_name).text.isNullOrBlank()
                && !findViewById<EditText>(R.id.email).text.isNullOrBlank()
                && !findViewById<EditText>(R.id.address).text.isNullOrBlank()
                && !findViewById<EditText>(R.id.zip_code).text.isNullOrBlank()
                && !findViewById<EditText>(R.id.city).text.isNullOrBlank()
                && !findViewById<EditText>(R.id.cardRef).text.isNullOrBlank()){

                val user = User(
                    findViewById<EditText>(R.id.first_name).text.toString(),
                    findViewById<EditText>(R.id.last_name).text.toString(),
                    findViewById<EditText>(R.id.email).text.toString(),
                    findViewById<EditText>(R.id.address).text.toString(),
                    findViewById<EditText>(R.id.zip_code).text.toString().toInt(),
                    findViewById<EditText>(R.id.city).text.toString(),
                    findViewById<EditText>(R.id.cardRef).text.toString().toInt()
                )
                UserStorage.saveUser(this, user)

                val newIntent = Intent(application, MainActivity::class.java)
                finishAffinity()
                startActivity(newIntent)
            }
            else{
                Toast.makeText(this, "Merci de remplir tout les champs", Toast.LENGTH_SHORT).show()
            }
        })
    }
}