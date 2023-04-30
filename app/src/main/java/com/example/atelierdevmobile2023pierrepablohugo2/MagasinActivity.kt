package com.example.atelierdevmobile2023pierrepablohugo2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso


class MagasinActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_magasin)
        // retrieve data from intent for the store id
        val storeIdString = intent.getStringExtra("storeId")
        val storeId = storeIdString?.toDoubleOrNull()

        // same for latitude and longitude
        val latitudeString = intent.getStringExtra("latitude")
        val latitude = latitudeString?.toDoubleOrNull()
        val longitudeString = intent.getStringExtra("longitude")
        val longitude = longitudeString?.toDoubleOrNull()

        val name = intent.getStringExtra("name")
        val desc = intent.getStringExtra("description")
        val imgUrl = intent.getStringExtra("imgUrl")
        val address = intent.getStringExtra("address")
        val zipcode = intent.getStringExtra("zipcode")
        val city = intent.getStringExtra("city")


        setHeaderTitle(name)

        findViewById<TextView>(R.id.adresseTextView).text = address
        findViewById<TextView>(R.id.codePostalTextView).text = zipcode + " " + city
        findViewById<TextView>(R.id.descriptionTextView).text = desc

        Picasso.get().load(imgUrl).into(findViewById<ImageView>(R.id.magasin_image))

        findViewById<TextView>(R.id.carteElem).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.offresElem).setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.magasinsElem).setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.profile).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}