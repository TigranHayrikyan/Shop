package com.example.shop.room_db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.viewModelFactory

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData: LiveData<List<Product>>
    private val repository: ProductRepository

    init {
        val productDao = ProductDatabase.getDatabase(application).productDao()
        repository = ProductRepository(productDao)
        readAllData = repository.readAllData
    }

    fun addProduct(product: Product) {
        repository.addProduct(product)
    }
}