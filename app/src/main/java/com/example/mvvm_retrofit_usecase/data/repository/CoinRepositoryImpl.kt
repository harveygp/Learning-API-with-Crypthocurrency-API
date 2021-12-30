package com.example.mvvm_retrofit_usecase.data.repository

import com.example.mvvm_retrofit_usecase.common.Resource
import com.example.mvvm_retrofit_usecase.data.remote.CoinPaprikaAPI
import com.example.mvvm_retrofit_usecase.data.remote.dto.CoinDetailDto
import com.example.mvvm_retrofit_usecase.data.remote.dto.CoinDto
import com.example.mvvm_retrofit_usecase.data.remote.dto.toCoin
import com.example.mvvm_retrofit_usecase.data.remote.dto.toCoinDetail
import com.example.mvvm_retrofit_usecase.domain.model.Coin
import com.example.mvvm_retrofit_usecase.domain.model.CoinDetail
import com.example.mvvm_retrofit_usecase.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class CoinRepositoryImpl @Inject constructor(
    private val api : CoinPaprikaAPI
) : CoinRepository{

    override suspend fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = api.getCoins().map{it.toCoin()}
            emit(Resource.Success(coins))
        } catch (e : HttpException){
            //if we get responde code that not start with 2 ( not succes )
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: " An Error"))
        } catch (e: IOException) {
            // if no internet or not talk
            emit((Resource.Error<List<Coin>>("couldn't search no internet")))
        }
    }

    override suspend fun getCoinById(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = api.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e : HttpException){
            //if we get responde code that not start with 2 ( not succes )
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: " An Error"))
        } catch (e: IOException) {
            // if no internet or not talk
            emit((Resource.Error<CoinDetail>("couldn't search no internet")))
        }
    }

}