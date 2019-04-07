package com.example.suhas.cancelledtrains.CancelledTrainDetails

import com.google.gson.annotations.SerializedName

data class CancelledTrainsPOJO(@SerializedName("response_code")
                               val responseCode: Int = 0,
                               @SerializedName("debit")
                               val debit: Int = 0,
                               @SerializedName("trains")
                               val trains: List<TrainsItem>?)