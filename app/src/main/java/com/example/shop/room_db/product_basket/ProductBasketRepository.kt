package com.example.shop.room_db.product_basket

import androidx.lifecycle.LiveData

class ProductBasketRepository(private val productBasketDao: ProductBasketDao) {
    val readAllBasketData: LiveData<List<ProductBasket>> = productBasketDao.getAllProducts()

    fun addProduct(productBasket: ProductBasket){
        productBasketDao.insert(productBasket)
    }
    fun deleteProduct(productBasket: ProductBasket){
        productBasketDao.delete(productBasket)
    }
}