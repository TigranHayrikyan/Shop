package com.example.shop.room_db.product_bookmark

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Product>>
    private val repository: ProductRepository

    init {
        val productDao = ProductDatabase.getDatabase(application).productDao()
        repository = ProductRepository(productDao)
        readAllData = repository.readAllData
    }

    fun addProduct(productBookmark: Product) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addProduct(productBookmark)
        }
    }
    fun deleteProduct(productBookmark: Product) {
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteProduct(productBookmark)
        }
    }
}