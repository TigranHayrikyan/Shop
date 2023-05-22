package com.example.shop.room_db.product_basket

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductBasket::class], version = 2, exportSchema = false)
abstract class ProductBasketDatabase : RoomDatabase(){
    abstract fun productBasketDao(): ProductBasketDao

    companion object{
        @Volatile
        private var INSTANCE : ProductBasketDatabase? = null

        fun getDatabase(context: Context): ProductBasketDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductBasketDatabase::class.java,
                    "product_database_basket"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}