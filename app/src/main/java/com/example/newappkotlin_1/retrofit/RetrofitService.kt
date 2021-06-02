package com.example.newappkotlin_1.retrofit

import com.example.newappkotlin_1.data_pojo.RatesPOJO
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("latest.js")
    suspend fun request(): Response<RatesPOJO>
}