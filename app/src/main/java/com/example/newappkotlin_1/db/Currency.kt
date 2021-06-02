package com.example.newappkotlin_1.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat

@Entity(tableName = "currency_table")
data class Currency(
    val result: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Long = System.currentTimeMillis()
) {
    val dateFormat: String
        get() = DateFormat.getDateTimeInstance().format(date)
}
