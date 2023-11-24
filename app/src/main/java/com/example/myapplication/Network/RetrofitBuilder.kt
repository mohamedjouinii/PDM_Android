package com.example.myapplication.Network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {companion object {
    private const val BASE_URL = "http://10.0.2.2:3000"
    fun create( ): Reclamation_interface {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return retrofit.create(Reclamation_interface::class.java)
    }

}
}