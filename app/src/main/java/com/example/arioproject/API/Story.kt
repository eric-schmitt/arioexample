package com.example.arioproject.API

import com.google.gson.annotations.SerializedName

data class Story (

    @SerializedName("by") val by : String,
    @SerializedName("descendants") val descendants : Int,
    @SerializedName("id") val id : Int,
    @SerializedName("kids") val kids : List<Int>,
    @SerializedName("score") val score : Int,
    @SerializedName("time") val time : Int,
    @SerializedName("title") val title : String,
    @SerializedName("type") val type : String,
    @SerializedName("url") val url : String
)