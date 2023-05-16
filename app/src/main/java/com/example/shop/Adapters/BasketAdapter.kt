package com.example.shop.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.room_db.Product

class BasketAdapter(
    private var onItemClickListener: OnItemClickListener

) : RecyclerView.Adapter<BasketAdapter.MyViewHolder>() {

    private var productList = emptyList<Product>()

    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.itemName)
        var price: TextView = itemView.findViewById(R.id.itemPrice)
        var imageView: ImageView = itemView.findViewById(R.id.itemImageView)
        var shopBasket: ImageView = itemView.findViewById(R.id.shop_basket)
        fun bind(product: Product, onItemClickListener: OnItemClickListener) {
            onItemClickListener.onItemClick(product)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProduct = productList[position]
        holder.title.text = currentProduct.nameList
        holder.price.text = currentProduct.priceList
        holder.imageView.setImageResource(currentProduct.imageList)
        holder.shopBasket.setImageResource(R.drawable.red_basket)
        holder.shopBasket.setOnClickListener {
            holder.bind(currentProduct, onItemClickListener)
        }
    }

    fun setData(product: List<Product>) {
        this.productList = product
        notifyDataSetChanged()
    }
}