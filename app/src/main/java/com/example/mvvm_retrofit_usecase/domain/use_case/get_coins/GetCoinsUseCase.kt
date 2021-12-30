package com.example.mvvm_retrofit_usecase.domain.use_case.get_coins

import com.example.mvvm_retrofit_usecase.common.Resource
import com.example.mvvm_retrofit_usecase.data.remote.dto.toCoin
import com.example.mvvm_retrofit_usecase.domain.model.Coin
import com.example.mvvm_retrofit_usecase.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    //Flow emit for multiple value over a time // loading or error data from Resource
    //List<Coin> will the one who emit with the type of coin
    suspend operator fun invoke() : Flow<Resource<List<Coin>>> = repository.getCoins()
}