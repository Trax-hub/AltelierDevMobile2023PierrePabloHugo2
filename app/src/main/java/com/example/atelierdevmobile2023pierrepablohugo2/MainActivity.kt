package com.example.atelierdevmobile2023pierrepablohugo2

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.atelierdevmobile2023pierrepablohugo2.Model.UserStorage
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix


class MainActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setHeaderTitle("Accueil")

        //On récupère l'utilisateur
        val user = UserStorage.getUser(this)
        //On génère le code barre et on l'affiche
        findViewById<TextView>(R.id.bar_code_text).setText(user?.cardRef.toString())
        val barcodeBitmap = generateBarcodeBitmap(user?.cardRef.toString(), BarcodeFormat.CODE_128, 600, 300)
        val barCode = findViewById<ImageView>(R.id.bar_code)
        barCode.setImageBitmap(barcodeBitmap)

        //On affiche le nom de l'utilisateur
        findViewById<TextView>(R.id.name).setText(user?.firstName + " " + user?.lastName)

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

    private fun generateBarcodeBitmap(barcodeData: String, barcodeFormat: BarcodeFormat, width: Int, height: Int ): Bitmap? {
        val multiFormatWriter = MultiFormatWriter()
        try {
            val bitMatrix: BitMatrix =
                multiFormatWriter.encode(barcodeData, barcodeFormat, width, height)
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(
                        x,
                        y,
                        if (bitMatrix[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt()
                    )
                }
            }
            return bitmap
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        return null
    }
}