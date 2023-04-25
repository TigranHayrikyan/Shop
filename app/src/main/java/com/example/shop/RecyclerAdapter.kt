package com.example.shop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

public class RecyclerAdapter(
    nameList: ArrayList<String>,
    priceList: ArrayList<String>,
    imageList: ArrayList<Int>,
    context: Context
) : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {

    private var nameList : ArrayList<String> = nameList
    private var priceList : ArrayList<String> = priceList
    private var imageList : ArrayList<Int> = imageList
    private var context : Context = context

    public class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var title : TextView  = itemView.findViewById(R.id.itemName)
         var price : TextView = itemView.findViewById(R.id.itemPrice)
         var imageView : ImageView = itemView.findViewById(R.id.itemImageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val rootView : View = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ItemViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.title.text = nameList[position]
        holder.price.text = priceList[position]
        holder.imageView.setImageResource(imageList[position])

    }

    override fun getItemCount(): Int {
        return nameList.size
    }
}