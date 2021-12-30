package com.example.mvvm_retrofit_usecase.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_retrofit_usecase.common.Constants
import com.example.mvvm_retrofit_usecase.common.Resource
import com.example.mvvm_retrofit_usecase.domain.use_case.get_coin.GetCoinUseCase
import com.example.mvvm_retrofit_usecase.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    //saving pass parameter ex : id
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = mutableStateOf<CoinDetailState>(CoinDetailState())
    val state : State<CoinDetailState> = _state

    init{
        //if its not equal to null, contain parameter navigation
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    // result mean it from function
    private fun getCoin(coinId : String){
        viewModelScope.launch (Dispatchers.IO){
            getCoinUseCase(coinId).onEach { result ->
                when(result){
                    is Resource.Success -> {
                        _state.value = CoinDetailState(coin = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = CoinDetailState(
                            error = result.message ?: "An Unexpected Error Occured"
                        )

                    }
                    is Resource.Loading ->{
                        _state.value = CoinDetailState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}