package com.example.ompaaloompa.data.models

import com.google.gson.annotations.SerializedName

data class UnsplashResponse(
    @SerializedName("current") val current: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("results") val results: List<Oompa>
)