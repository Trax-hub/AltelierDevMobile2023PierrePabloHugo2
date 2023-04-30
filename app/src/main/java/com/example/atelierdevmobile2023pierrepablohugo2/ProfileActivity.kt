package com.example.atelierdevmobile2023pierrepablohugo2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.atelierdevmobile2023pierrepablohugo2.Model.User
import com.example.atelierdevmobile2023pierrepablohugo2.Model.UserStorage

class ProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        showBack()
        setHeaderTitle("Compte")

        val currentUser = UserStorage.getUser(this)

        //on remplit les champs avec les données de l'utilisateur
        findViewById<EditText>(R.id.first_name).setText(currentUser?.firstName.toString())
        findViewById<EditText>(R.id.last_name).setText(currentUser?.lastName.toString())
        findViewById<EditText>(R.id.email).setText(currentUser?.email.toString())
        findViewById<EditText>(R.id.address).setText(currentUser?.address.toString())
        findViewById<EditText>(R.id.zip_code).setText(currentUser?.zipcode.toString())
        findViewById<EditText>(R.id.city).setText(currentUser?.city.toString())

        //On enregistre les modifications
        val saveButton = findViewById<AppCompatButton>(R.id.save_button)
        saveButton.setOnClickListener {
            if (!findViewById<EditText>(R.id.first_name).text.isNullOrBlank()
                && !findViewById<EditText>(R.id.last_name).text.isNullOrBlank()
                && !findViewById<EditText>(R.id.email).text.isNullOrBlank()
                && !findViewById<EditText>(R.id.address).text.isNullOrBlank()
                && !findViewById<EditText>(R.id.zip_code).text.isNullOrBlank()
                && !findViewById<EditText>(R.id.city).text.isNullOrBlank()
            ) {
                val user = User(
                    findViewById<EditText>(R.id.first_name).text.toString(),
                    findViewById<EditText>(R.id.last_name).text.toString(),
                    findViewById<EditText>(R.id.email).text.toString(),
                    findViewById<EditText>(R.id.address).text.toString(),
                    findViewById<EditText>(R.id.zip_code).text.toString().toInt(),
                    findViewById<EditText>(R.id.city).text.toString(),
                    currentUser?.cardRef.toString().toInt())

                UserStorage.saveUser(this, user)

                Toast.makeText(this, "Modifications enregistrées", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Merci de remplir tout les champs", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<TextView>(R.id.offresElem).setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }


        var disconnectButton = findViewById<AppCompatButton>(R.id.disconnect_button)
        disconnectButton.setOnClickListener(View.OnClickListener {
            UserStorage.deleteMyObject(this)
            val newIntent = Intent(application, AccountActivity::class.java)
            finishAffinity()
            startActivity(newIntent)
        })

    }

}
