package com.app.mvvmroomdb.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET(ApiConstants.memes)
    fun getMemes(): Call<JsonObject>
}