package com.example.suhas.cancelledtrains

import com.example.suhas.cancelledtrains.CancelledTrainDetails.CancelledTrainsPOJO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CanTrainINFF {
    @GET("v2/cancelled/date/{date}/apikey/91umr8ozt9/")
    fun loadTotalData(@Path("date") s:String): Call<CancelledTrainsPOJO>
}