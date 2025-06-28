package com.example.hpapi.api

import com.example.hpapi.data.Character
import com.example.hpapi.data.Spell
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface HpApiService {


    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") characterId: String): Response<List<Character>>


    @GET("characters/staff")
    suspend fun getHogwartsStaff(): Response<List<Character>>


    @GET("characters/house/{houseName}")
    suspend fun getStudentsByHouse(@Path("houseName") house: String): Response<List<Character>>


    @GET("spells")
    suspend fun getAllSpells(): Response<List<Spell>>
}


object RetrofitClient {
    private const val BASE_URL = "https://hp-api.onrender.com/api/"

    val instance: HpApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(HpApiService::class.java)
    }
}