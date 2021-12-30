package com.example.mvvm_retrofit_usecase.presentation.coin_list

import com.example.mvvm_retrofit_usecase.domain.model.Coin

data class CoinListState(
    val isLoading : Boolean = false,
    val coins : List<Coin> = emptyList(),
    val error : String = ""
)
