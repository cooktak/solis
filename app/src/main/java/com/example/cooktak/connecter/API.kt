package com.example.cooktak.connecter

import com.example.cooktak.model.barcodeModel
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    //바코드 스캔
    @POST("/api/icon_search_main")
    fun putCode(@Body body: JsonObject): Call<barcodeModel>

    @POST("/api/user/sign_up")
    fun registerUser(@Body body: JsonObject): Call<Void>
}