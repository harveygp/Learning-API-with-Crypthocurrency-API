package com.example.mvvm_retrofit_usecase.presentation.coin_detail

import com.example.mvvm_retrofit_usecase.domain.model.Coin
import com.example.mvvm_retrofit_usecase.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading : Boolean = false,
    val coin : CoinDetail? = null,
    val error : String = ""
)
