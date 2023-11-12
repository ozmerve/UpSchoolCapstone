package com.merveoz.capstone1.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.merveoz.capstone1.data.model.response.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductRoomDB : RoomDatabase() {

    abstract fun productDao(): ProductDao
}