package com.example.mvvm_retrofit_usecase.domain.repository

import com.example.mvvm_retrofit_usecase.common.Resource
import com.example.mvvm_retrofit_usecase.data.remote.dto.CoinDetailDto
import com.example.mvvm_retrofit_usecase.data.remote.dto.CoinDto
import com.example.mvvm_retrofit_usecase.domain.model.Coin
import com.example.mvvm_retrofit_usecase.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow


interface CoinRepository {

    suspend fun getCoins(): Flow<Resource<List<Coin>>>

    suspend fun getCoinById(coinId: String) : Flow<Resource<CoinDetail>>
}