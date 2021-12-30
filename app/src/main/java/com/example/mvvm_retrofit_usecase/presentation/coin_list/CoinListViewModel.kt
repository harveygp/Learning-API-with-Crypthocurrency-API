package com.example.mvvm_retrofit_usecase.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_retrofit_usecase.common.Resource
import com.example.mvvm_retrofit_usecase.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
   private val getCoinsUseCase: GetCoinsUseCase
): ViewModel(){

    private val _state = mutableStateOf<CoinListState>(CoinListState())
    val state : State<CoinListState> = _state

    init{
        getCoins()
    }

    // result mean it from function
    private fun getCoins(){
        viewModelScope.launch(Dispatchers.IO){
            getCoinsUseCase().onEach { result ->
                when(result){
                    is Resource.Success -> {
                        _state.value = CoinListState(coins = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value = CoinListState(
                            error = result.message ?: "An Unexpected Error Occured"
                        )

                    }
                    is Resource.Loading ->{
                        _state.value = CoinListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}