package com.example.shop.room_db.product_bookmark

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
    fun insert(productBookmark: Product)

    @Update
    fun update(productBookmark: Product)

    @Delete
    fun delete(productBookmark: Product)

    @Query("SELECT * FROM product_table ORDER BY id ASC")
    fun getAllProducts() : LiveData<List<Product>>

    @Query("SELECT * FROM product_table WHERE id LIKE :id")
    fun getProduct(id : Int) : Product

}