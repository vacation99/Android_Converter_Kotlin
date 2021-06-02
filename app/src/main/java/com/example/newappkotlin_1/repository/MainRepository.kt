package com.example.newappkotlin_1.repository

import com.example.newappkotlin_1.data_pojo.RatesPOJO
import com.example.newappkotlin_1.db.Currency
import com.example.newappkotlin_1.db.CurrencyDao
import com.example.newappkotlin_1.retrofit.RetrofitService
import com.example.newappkotlin_1.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val retrofitService: RetrofitService,
    private val currencyDao: CurrencyDao
) {

    suspend fun getResponseFromRetrofit(): Resource<RatesPOJO?> {
        return withContext(Dispatchers.IO) {
            try {
                val response = retrofitService.request()
                if (response.isSuccessful) {
                    Resource.Success(response.body())
                } else {
                    Resource.Error(response.message())
                }
            } catch (e: Exception) {
                Resource.Error(e.message ?: "Error")
            }
        }
    }

    suspend fun insertData(currency: Currency) {
        currencyDao.insert(currency)
    }

    suspend fun loadData(): List<Currency> {
        return withContext(Dispatchers.IO) {
            currencyDao.loadAll()
        }
    }

    suspend fun clearData() {
        currencyDao.clearAll()
    }
}