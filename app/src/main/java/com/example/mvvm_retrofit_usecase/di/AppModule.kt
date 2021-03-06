package com.example.mvvm_retrofit_usecase.di

import com.example.mvvm_retrofit_usecase.common.Constants
import com.example.mvvm_retrofit_usecase.data.remote.CoinPaprikaAPI
import com.example.mvvm_retrofit_usecase.data.repository.CoinRepositoryImpl
import com.example.mvvm_retrofit_usecase.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)//how long this depedency will live
object AppModule {

    @Provides
    @Singleton //making sure only single insetence
    fun providePaprikaApi() : CoinPaprikaAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //use Gson data
            .build()
            .create(CoinPaprikaAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api : CoinPaprikaAPI) : CoinRepository{
        return CoinRepositoryImpl(api)
    }
}