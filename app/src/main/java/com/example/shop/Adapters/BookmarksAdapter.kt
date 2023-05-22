package com.example.shop.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.room_db.product_bookmark.Product

class BookmarksAdapter(
    onItemClickListener: OnItemClickListener

) : RecyclerView.Adapter<BookmarksAdapter.MyViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(productBookmark: Product)
    }

    private var productBookmarkList = emptyList<Product>()
    private var onItemClickListener: OnItemClickListener = onItemClickListener


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.itemName)
        var price: TextView = itemView.findViewById(R.id.itemPrice)
        var imageView: ImageView = itemView.findViewById(R.id.itemImageView)
        var bookmark: ImageView = itemView.findViewById(R.id.bookmark)
        val basket: ImageView = itemView.findViewById(R.id.shop_basket)
        fun bind(productBookmark: Product, onItemClickListener: OnItemClickListener) {
            onItemClickListener.onItemClick(productBookmark)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return productBookmarkList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProduct = productBookmarkList[position]
        holder.title.text = currentProduct.nameList
        holder.price.text = currentProduct.priceList
        holder.imageView.setImageResource(currentProduct.imageList)
        holder.basket.isVisible = false
        holder.bookmark.setOnClickListener {
            holder.bind(currentProduct, onItemClickListener)
        }
    }

    fun setData(productBookmark: List<Product>) {
        this.productBookmarkList = productBookmark
        notifyDataSetChanged()
    }
}