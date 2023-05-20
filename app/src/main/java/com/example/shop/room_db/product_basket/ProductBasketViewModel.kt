package com.example.shop.room_db.product_basket

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductBasketViewModel(application: Application) : AndroidViewModel(application) {
    val readAllBasketData: LiveData<List<ProductBasket>>
    private val repository: ProductBasketRepository

    init {
        val productDao = ProductBasketDatabase.getDatabase(application).productBasketDao()
        repository = ProductBasketRepository(productDao)
        readAllBasketData = repository.readAllBasketData
    }

    fun addProduct(productBasket: ProductBasket) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addProduct(productBasket)
        }
    }
    fun deleteProduct(productBasket: ProductBasket) {
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteProduct(productBasket)
        }
    }
}