package com.example.shop.Adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.room_db.product_basket.ProductBasket
import com.example.shop.room_db.product_bookmark.Product

class BasketAdapter(
    private var onItemClickListener: OnItemClickListener

) : RecyclerView.Adapter<BasketAdapter.MyViewHolder>() {

    private var productBasketList = emptyList<ProductBasket>()

    interface OnItemClickListener {
        fun onItemClick(productBasket: ProductBasket)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.itemName)
        var price: TextView = itemView.findViewById(R.id.itemPrice)
        var imageView: ImageView = itemView.findViewById(R.id.itemImageView)
        var shopBasket: ImageView = itemView.findViewById(R.id.shop_basket)
        val bookmark: ImageView = itemView.findViewById(R.id.bookmark)
        fun bind(productBasket: ProductBasket, onItemClickListener: OnItemClickListener) {
            onItemClickListener.onItemClick(productBasket)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return productBasketList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProduct = productBasketList[position]
        holder.title.text = currentProduct.nameList
        holder.price.text = currentProduct.priceList.toString()
        holder.imageView.setImageResource(currentProduct.imageList)
        holder.bookmark.isVisible = false
        holder.shopBasket.setOnClickListener {
            holder.bind(currentProduct, onItemClickListener)
        }
    }

    fun setData(productBasket: List<ProductBasket>) {
        this.productBasketList = productBasket
        notifyDataSetChanged()
    }
}