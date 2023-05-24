package com.example.shop.room_db.product_bookmark

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nameList: String,
    val priceList: Int,
    val imageList: Int
)