package com.example.shop.room_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("SELECT * FROM product_table ORDER BY id ASC")
    fun getAllProducts() : LiveData<List<Product>>

    @Query("SELECT * FROM product_table WHERE id LIKE :id")
    fun getProduct(id : Int) : Product

}