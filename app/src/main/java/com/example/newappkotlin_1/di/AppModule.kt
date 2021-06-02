package com.example.newappkotlin_1.di

import android.app.Application
import androidx.room.Room
import com.example.newappkotlin_1.db.CurrencyDatabase
import com.example.newappkotlin_1.retrofit.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, CurrencyDatabase::class.java, "currency_database").build()

    @Provides
    @Singleton
    fun provideDatabaseDao(currencyDatabase: CurrencyDatabase) = currencyDatabase.currencyDao()
}