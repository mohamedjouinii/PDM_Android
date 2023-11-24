package com.example.myapplication.Network


import android.provider.MediaStore
import com.example.myapplication.models.Reclamation
import com.google.gson.JsonObject
import retrofit2.Call

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Reclamation_interface {
    @POST("/reclamation/")
    suspend fun create_reclamation (@Body reclamation: com.example.myapplication.models.Reclamation): Response<JsonObject>

    @POST("/magazine/")
    suspend fun create_magazine (@Body magazine: MediaStore.Video) : Response<JsonObject>
    @GET("/reclamation/")
    fun getAllReclamation() : Call<List<Reclamation>>



}