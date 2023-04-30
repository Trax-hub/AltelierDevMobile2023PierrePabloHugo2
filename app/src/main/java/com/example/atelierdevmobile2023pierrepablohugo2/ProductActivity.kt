package com.example.atelierdevmobile2023pierrepablohugo2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import okio.IOException
import org.json.JSONObject

class ProductActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        setHeaderTitle("Produits")

        val products = arrayListOf<Product>()
        val url="https://img.freepik.com/photos-gratuite/rendu-3d-fond-espace-planetes-abstraites-nebuleuse_1048-12994.jpg?w=740&t=st=1673970224~exp=1673970824~hmac=5db29203b7bcddbecb50f50b6bab3920b729055ce5b87aaccc3260362a801f19"

        val recyclerviewProducts=findViewById<RecyclerView>(R.id.productsRecyclerView)
        recyclerviewProducts.layoutManager = LinearLayoutManager(this)

        val rayonAdapter=ProductAdapter(products)
        recyclerviewProducts.adapter=rayonAdapter

        findViewById<ImageView>(R.id.profile).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.magasinsElem).setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        val requestUrl="https://www.ugarit.online/epsi/offers.json"
        val request = Request.Builder()
            .url(requestUrl)
            .get()
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()

                if(data!=null) {
                    val jsProducts = JSONObject(data)
                    val jsArrayProducts = jsProducts.getJSONArray("items")
                    for (i in 0 until jsArrayProducts.length()) {
                        val js = jsArrayProducts.getJSONObject(i)
                        val product = Product(
                            js.optString("name", "not found"),
                            js.optString("description", "not found"),
                            js.optString("picture_url", url)
                        )
                        products.add(product)
                        runOnUiThread(Runnable {
                            rayonAdapter.notifyDataSetChanged()
                        })
                    }
                }
            }

        })
    }
}