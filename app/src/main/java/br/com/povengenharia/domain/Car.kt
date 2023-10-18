package br.com.povengenharia.domain

import com.google.gson.annotations.SerializedName

data class Car (
    @SerializedName("id") val id: Int,
    @SerializedName("modelName") val modelName : String,
    @SerializedName("preco") val price : String,
    @SerializedName("bateria") val battery : String,
    @SerializedName("potencia") val horsePower : String,
    @SerializedName("recarga") val recharge : String,
    @SerializedName("urlPhoto") val urlPhoto: String,
    var isFavorite: Boolean
)