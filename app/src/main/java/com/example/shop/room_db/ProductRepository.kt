package com.example.shop.room_db

import androidx.lifecycle.LiveData

class ProductRepository(private val productDao: ProductDao) {
    val readAllData: LiveData<List<Product>> = productDao.getAllProducts()

    fun addProduct(product: Product){
        productDao.insert(product)
    }
}