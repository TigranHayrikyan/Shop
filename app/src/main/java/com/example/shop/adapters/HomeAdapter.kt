package com.example.shop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.room_db.product_basket.ProductBasket
import com.example.shop.room_db.product_bookmark.Product
import com.google.android.material.snackbar.Snackbar

class HomeAdapter(
    idList: ArrayList<Int>,
    nameList: ArrayList<String>,
    priceList: ArrayList<Int>,
    imageList: ArrayList<Int>,
    onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<HomeAdapter.ItemViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(productBookmark: Product)
        fun onBasketItemClick(productBasket: ProductBasket)
    }

    private var idList: ArrayList<Int> = idList
    private var nameList: ArrayList<String> = nameList
    private var priceList: ArrayList<Int> = priceList
    private var imageList: ArrayList<Int> = imageList
    private var onItemClickListener: OnItemClickListener = onItemClickListener

    public class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.itemName)
        var price: TextView = itemView.findViewById(R.id.itemPrice)
        var imageView: ImageView = itemView.findViewById(R.id.itemImageView)
        var bookmark: ImageView = itemView.findViewById(R.id.bookmark)
        var shopBasket: ImageView = itemView.findViewById(R.id.shop_basket)

        fun bind(productBookmark: Product, onItemClickListener: OnItemClickListener) {
            onItemClickListener.onItemClick(productBookmark)

        }

        fun bindBasket(productBasket: ProductBasket, onItemClickListener: OnItemClickListener) {
            onItemClickListener.onBasketItemClick(productBasket)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val rootView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ItemViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.title.text = nameList[position]
        holder.price.text = priceList[position].toString()
        holder.imageView.setImageResource(imageList[position])
        val productBookmark =
            Product(idList[position], nameList[position], priceList[position], imageList[position])
        holder.bookmark.setOnClickListener {
            holder.bind(productBookmark, onItemClickListener)
            holder.itemView.animate().apply {
                duration = 1000
                rotationYBy(360f)
            }.start()
            Snackbar.make(it, "Product added to Bookmark", Snackbar.LENGTH_SHORT).show()
        }

        val productBasket =
            ProductBasket(
                idList[position],
                nameList[position],
                priceList[position],
                imageList[position]
            )
        holder.shopBasket.setOnClickListener {
            holder.bindBasket(productBasket, onItemClickListener)
            holder.itemView.animate().apply {
                duration = 1000
                rotationYBy(360f)
            }.start()
            Snackbar.make(it, "Product added to Basket", Snackbar.LENGTH_SHORT).show()

        }
    }

    override fun getItemCount(): Int {
        return nameList.size
    }
}