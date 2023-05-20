package com.example.shop.room_db.product_bookmark

import androidx.lifecycle.LiveData

class ProductRepository(private val productDao: ProductDao) {
    val readAllData: LiveData<List<Product>> = productDao.getAllProducts()

    fun addProduct(productBookmark: Product){
        productDao.insert(productBookmark)
    }
    fun deleteProduct(productBookmark: Product){
        productDao.delete(productBookmark)
    }
}