package com.app.mvvmroomdb.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{

        private var mInstance : RetrofitClient = RetrofitClient()
        private lateinit var retrofit: Retrofit

        @Synchronized fun getInstance(): RetrofitClient{
            if (mInstance == null){
                mInstance = RetrofitClient()
            }

            return mInstance
        }

    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApi(): ApiService{
        return retrofit.create(ApiService::class.java)
    }

}