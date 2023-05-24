package com.example.shop.room_db.product_basket

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table_basket")
data class ProductBasket(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nameList: String,
    val priceList: Int,
    val imageList: Int
)

