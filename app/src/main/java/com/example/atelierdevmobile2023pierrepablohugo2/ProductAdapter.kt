package com.example.atelierdevmobile2023pierrepablohugo2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view:View) :RecyclerView.ViewHolder(view){
        val nameTextView = view.findViewById<TextView>(R.id.product_name)
        val descTextView = view.findViewById<TextView>(R.id.product_description)
        val imageView= view.findViewById<ImageView>(R.id.product_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        holder.nameTextView.text = product.nom
        holder.descTextView.text = product.desc
        Picasso.get().load(product.img).into(holder.imageView)
        Toast.makeText(holder.descTextView.context,product.nom,Toast.LENGTH_SHORT).show()
    }

    override fun getItemCount(): Int {
        return productList.size
    }


}