package com.example.atelierdevmobile2023pierrepablohugo2
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.*;
import org.json.JSONObject
import java.io.IOException

internal class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
    private val requestUrl="https://www.ugarit.online/epsi/stores.json"
    private val request = Request.Builder()
        .url(requestUrl)
        .get()
        .cacheControl(CacheControl.FORCE_NETWORK)
        .build()

    val pointsMap = arrayListOf<PointMap>()
    val urlDefault ="https://img.freepik.com/photos-gratuite/rendu-3d-fond-espace-planetes-abstraites-nebuleuse_1048-12994.jpg?w=740&t=st=1673970224~exp=1673970824~hmac=5db29203b7bcddbecb50f50b6bab3920b729055ce5b87aaccc3260362a801f19"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val data = response.body?.string()

                if(data!=null) {
                    val dataFetch = JSONObject(data)
                    val arrayPoints = dataFetch.getJSONArray("stores")
                    for (i in 0 until arrayPoints.length()) {
                        val js = arrayPoints.getJSONObject(i)
                        val point = PointMap(
                            js.optInt("storeId", -1),
                            js.optString("name", "not found"),
                            js.optString("description", "not found"),
                            js.optString("pictureStore", urlDefault),
                            js.optString("address", "not found"),
                            js.optString("zipcode", "00000"),
                            js.optString("city", "not found"),
                            js.optDouble("longitude", 0.0),
                            js.optDouble("latitude", 0.0),

                            )
                        pointsMap.add(point)
                    }

                    println("Points map : "+pointsMap.size)
                    runOnUiThread {
                        for (point in pointsMap) {
                            mMap.addMarker(MarkerOptions()
                                .position(LatLng(point.longitude, point.latitude))
                                .title(point.nom)
                                .snippet(point.adresse+" "+point.codePostal+" "+point.ville))
                        }
                    }
                }

            }

        })

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val pointTest = LatLng( 48.0, 3.0)

        mMap.moveCamera(CameraUpdateFactory.newLatLng(pointTest))
    }
}

      