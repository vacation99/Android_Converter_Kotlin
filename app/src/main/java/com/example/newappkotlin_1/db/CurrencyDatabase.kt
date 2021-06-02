package com.example.newappkotlin_1.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Currency::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}