package com.example.cooktak.connecter

import com.example.cooktak.model.barcodeModel
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface API {
    //바코드 스캔
    @POST("/api/icon_search_main")
    fun putCode(@Body body: JsonObject): Call<barcodeModel>

    @PUT("/api/v1/user/new")
    fun registerUser(@Body body: JsonObject): Call<Void>
}