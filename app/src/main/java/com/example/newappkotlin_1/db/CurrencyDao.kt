package com.example.newappkotlin_1.db

import androidx.room.*

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency_table")
    suspend fun loadAll(): List<Currency>

    @Query("DELETE FROM currency_table WHERE id >= 0")
    suspend fun clearAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currency: Currency)

    @Update
    suspend fun update(currency: Currency)

    @Delete
    suspend fun delete(currency: Currency)
}