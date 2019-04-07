package com.example.suhas.cancelledtrains.CancelledTrainDetails

import com.google.gson.annotations.SerializedName

data class Source(@SerializedName("code")
                  val code: String = "",
                  @SerializedName("name")
                  val name: String = "")