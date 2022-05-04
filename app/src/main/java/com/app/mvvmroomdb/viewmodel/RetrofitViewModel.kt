package com.app.mvvmroomdb.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.google.gson.JsonObject
import com.app.mvvmroomdb.api.RetrofitClient
import com.app.mvvmroomdb.data.MemeData
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class RetrofitViewModel(application: Application): AndroidViewModel(application) {

    private var list: MutableLiveData<ArrayList<MemeData>> = MutableLiveData(arrayListOf<MemeData>())
    val list_: LiveData<ArrayList<MemeData>>
        get() = list

    init {
        list.value = arrayListOf<MemeData>()
        getDataFromRetrofit()
    }

    fun getDataFromRetrofit(){
        viewModelScope.launch {
            var data = RetrofitClient.getInstance().getApi().getMemes()

            data.enqueue(object: Callback<JsonObject>{
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    Log.w("valuelist", "success")
                    list.value = getList(response.body())
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.w("valuelist", "failed $t")
                }

            })

        }
    }

    private fun getList(obj: JsonObject?): ArrayList<MemeData> {
        val indata = obj?.get("data")?.asJsonObject
        val memeslist = indata?.get("memes")?.asJsonArray

        val tmplist = arrayListOf<MemeData>()
        for (meme in memeslist!!){
            val obj = meme.asJsonObject
            val data = MemeData(obj.get("url").asString)
            tmplist.add(data)
        }

        return tmplist
    }
}