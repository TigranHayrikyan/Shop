package com.example.shop.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.room_db.Product

class HomeAdapter(
    idList: ArrayList<Int>,
    nameList: ArrayList<String>,
    priceList: ArrayList<String>,
    imageList: ArrayList<Int>,
    context: Context,
    onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<HomeAdapter.ItemViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }

    private var idList: ArrayList<Int> = idList
    private var nameList: ArrayList<String> = nameList
    private var priceList: ArrayList<String> = priceList
    private var imageList: ArrayList<Int> = imageList
    private var context: Context = context
    private var onItemClickListener: OnItemClickListener = onItemClickListener


    public class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.itemName)
        var price: TextView = itemView.findViewById(R.id.itemPrice)
        var imageView: ImageView = itemView.findViewById(R.id.itemImageView)
        var bookmark: ImageView = itemView.findViewById(R.id.bookmark)
        var shopBasket: ImageView = itemView.findViewById(R.id.shop_basket)


        fun bind(product: Product, onItemClickListener: OnItemClickListener) {
            onItemClickListener.onItemClick(product)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val rootView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ItemViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.title.text = nameList[position]
        holder.price.text = priceList[position]
        holder.imageView.setImageResource(imageList[position])
        val product =
            Product(idList[position], nameList[position], priceList[position], imageList[position])
        holder.bookmark.setOnClickListener {
            holder.bind(product, onItemClickListener)
        }
        holder.shopBasket.setOnClickListener {
            holder.bind(product, onItemClickListener)
        }

    }

    override fun getItemCount(): Int {
        return nameList.size
    }
}