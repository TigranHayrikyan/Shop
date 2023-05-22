package com.example.shop.room_db.product_basket

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductBasketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(productBasket: ProductBasket)

    @Update
    fun update(productBasket: ProductBasket)

    @Delete
    fun delete(productBasket: ProductBasket)

    @Query("SELECT * FROM product_table_basket ORDER BY id ASC")
    fun getAllProducts() : LiveData<List<ProductBasket>>

    @Query("SELECT * FROM product_table_basket WHERE id LIKE :id")
    fun getProduct(id : Int) : ProductBasket
}