package com.example.mvvm_retrofit_usecase.data.remote

import com.example.mvvm_retrofit_usecase.data.remote.dto.CoinDetailDto
import com.example.mvvm_retrofit_usecase.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaAPI {

    @GET("/v1/coins")
    suspend fun getCoins() : List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId")coinId: String) : CoinDetailDto
}